package de.ralfhergert.dota2.autochess.character;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.HpRegenerationAbility;
import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;
import de.ralfhergert.dota2.autochess.damage.SpellDamage;
import de.ralfhergert.dota2.autochess.event.CharacterBeingDamagedEvent;
import de.ralfhergert.dota2.autochess.event.CharacterBeingHitEvent;
import de.ralfhergert.dota2.autochess.event.CharacterDiedEvent;
import de.ralfhergert.dota2.autochess.event.CharacterEvadedAttackEvent;
import de.ralfhergert.dota2.autochess.event.Event;
import de.ralfhergert.dota2.autochess.modifier.ArmorModifier;
import de.ralfhergert.dota2.autochess.modifier.ChanceOfBeingHitModifier;
import de.ralfhergert.dota2.autochess.modifier.MagicResistanceModifier;
import de.ralfhergert.dota2.autochess.modifier.MaxHealthModifier;
import de.ralfhergert.dota2.autochess.modifier.VisibilityModifier;
import de.ralfhergert.dota2.autochess.modifier.Modifier;
import de.ralfhergert.dota2.autochess.modifier.ReceivingAutoAttackDamageModifier;
import de.ralfhergert.dota2.autochess.modifier.ReceivingSpellDamageModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A character is a chess piece on board.
 */
public class Character {

    private static final Logger LOG = LoggerFactory.getLogger(Character.class);

    private final String team;
    private int maxHealth;
    private int currentHealth;
    private int armor;
    private double magicResistance;
    private int currentMana;
    private int maxMana = 100;

    private final List<Ability> abilities = new ArrayList<>();
    private final List<Modifier> modifiers = new ArrayList<>();

    private Arena arena;
    private Character currentTarget = null;

    public Character(String team, int maxHealth, int armor, double magicResistance) {
        this.team = team;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.armor = armor;
        this.magicResistance = magicResistance;
        addAbility(new HpRegenerationAbility(0));
    }

    public Character addAbility(Ability ability) {
        ability.setOwner(this);
        abilities.add(ability);
        return this;
    }

    public Stream<Ability> getAbilities() {
        return abilities.stream();
    }

    public Character addModifier(Modifier modifier) {
        modifiers.add(modifier);
        if (arena != null) {
            modifier.initialize(arena);
        }
        return this;
    }

    public Stream<Modifier> getModifiers() {
        return modifiers.stream();
    }

    public void removeModifier(Predicate<Modifier> predicate) {
        modifiers.removeIf(predicate);
    }

    public Character initialize(Arena arena) {
        this.arena = arena;
        // abilities may add further abilities when being initialized.
        new ArrayList<>(abilities).forEach(ability -> ability.initialize(arena));
        modifiers.forEach(modifier -> modifier.initialize(arena));
        return this;
    }

    public Character performOn(Arena arena) {
        if (!isAlive()) {
            return this; // nothing to do character is dead
        }
        if (currentTarget == null || !currentTarget.isAlive()) {
            LOG.info("{} trying to find a target in arena", describe());
            currentTarget = findNextLivingTarget(arena);
        }
        if (currentTarget == null) {
            LOG.info("{} having no current target in arena", describe());
            return this; // no more target in arena.
        }
        abilities.forEach(ability -> ability.performOn(arena));
        return this;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public String getTeam() {
        return team;
    }

    public Character findNextLivingTarget(Arena arena) {
        return arena.getEnemiesOf(team)
            .filter(Character::isAlive)
            .findFirst()
            .orElse(null);
    }

    public Character getCurrentTarget() {
        return currentTarget;
    }

    public boolean isVisible() {
        return applyModifiers(true, VisibilityModifier.class);
    }

    public Character tryToInflictDamage(Arena arena, AutoAttackAbility ability, AutoAttackDamage autoAttackDamage) {
        final double chanceOfBeingHit = applyModifiers(1.0, ChanceOfBeingHitModifier.class);
        if (arena.getRandom().nextDouble() >= chanceOfBeingHit) { // character evaded the attack.
            arena.onEvent(new CharacterEvadedAttackEvent(arena, this, ability));
            return this;
        }
        arena.onEvent(new CharacterBeingHitEvent(arena, this, ability));

        final AutoAttackDamage damage = applyModifiers(autoAttackDamage, ReceivingAutoAttackDamageModifier.class);
        final int healthBefore = currentHealth;
        final double effectiveArmor = getArmor();
        final double damageMultiplier = 1 - ((0.052 * effectiveArmor) / (0.9 + 0.048 * Math.abs(effectiveArmor)));
        currentHealth -= Math.max(0, damage.getDamage() * damageMultiplier);
        if (healthBefore > currentHealth) {
            arena.onEvent(new CharacterBeingDamagedEvent(arena, this, damage.getSource(), healthBefore - currentHealth));
        }
        if (currentHealth <= 0) {
            arena.onEvent(new CharacterDiedEvent(arena, this));
        }
        return this;
    }

    public Character tryToInflictDamage(Arena arena, Ability ability, SpellDamage spellDamage) {
        // character may evade the attack.
        arena.onEvent(new CharacterBeingHitEvent(arena, this, ability));
        SpellDamage damage = applyModifiers(spellDamage, ReceivingSpellDamageModifier.class);

        final double magicResistance = applyModifiers(this.magicResistance, MagicResistanceModifier.class);
        if (magicResistance >= 1) {
            return this; // character is immune to magic damage.
        }
        final int healthBefore = currentHealth;
        currentHealth -= Math.max(0, damage.getDamage() * (1 - magicResistance));
        if (healthBefore > currentHealth) {
            arena.onEvent(new CharacterBeingDamagedEvent(arena, this, damage.getSource(), healthBefore - currentHealth));
        }
        if (currentHealth <= 0) {
            arena.onEvent(new CharacterDiedEvent(arena, this));
        }
        return this;
    }

    public double getArmor() {
        return applyModifiers(armor, ArmorModifier.class);
    }

    public Character onEvent(Event event) {
        abilities.forEach(ability -> ability.onEvent(event));
        modifiers.forEach(modifier -> modifier.onEvent(event));
        return this;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxHealth() {
        return applyModifiers(maxHealth, MaxHealthModifier.class);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * This method will run the given value through all modifiers which match the given modifierClass.
     */
    public <Type> Type applyModifiers(Type value, Class<? extends Modifier<Type>> modifierClass) {
        Type currentValue = value;
        for (Modifier modifier : modifiers) {
            if (modifierClass.isInstance(modifier)) {
                currentValue = modifierClass.cast(modifier).modify(currentValue);
            }
        }
        return currentValue;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
            "team='" + team + '\'' +
            ", health=" + currentHealth + "/" + getMaxHealth() +
            ", mana=" + currentMana + "/" + maxMana +
            ", armor=" + armor +
            ", magicResistance=" + magicResistance +
            ", abilities=" + abilities +
            ", modifiers=" + modifiers +
            '}';
    }

    public String describe() {
        return getClass().getSimpleName() + " from " + team;
    }
}
