package de.ralfhergert.dota2.autochess.character;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;
import de.ralfhergert.dota2.autochess.damage.SpellDamage;
import de.ralfhergert.dota2.autochess.event.CharacterBeingDamagedEvent;
import de.ralfhergert.dota2.autochess.event.CharacterBeingHitEvent;
import de.ralfhergert.dota2.autochess.event.CharacterDiedEvent;
import de.ralfhergert.dota2.autochess.event.Event;
import de.ralfhergert.dota2.autochess.modifier.Invisibility;
import de.ralfhergert.dota2.autochess.modifier.Modifier;
import de.ralfhergert.dota2.autochess.modifier.ReceivingAutoAttackDamageModifier;
import de.ralfhergert.dota2.autochess.modifier.ReceivingSpellDamageModifier;

import java.util.ArrayList;
import java.util.List;

public class Character {

    private final String team;
    private int maxHealth;
    private int currentHealth;
    private int armor;
    private int magicResistance;
    private int currentMana;
    private int maxMana = 100;

    private final List<Ability> abilities = new ArrayList<>();
    private final List<Modifier> modifiers = new ArrayList<>();

    private Character currentTarget = null;

    public Character(String team, int maxHealth, int armor, int magicResistance) {
        this.team = team;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.armor = armor;
        this.magicResistance = magicResistance;
    }

    public Character addAbility(Ability ability) {
        ability.setOwner(this);
        abilities.add(ability);
        return this;
    }

    public Character initialize(Arena arena) {
        abilities.forEach(ability -> ability.initialize(arena));
        modifiers.forEach(modifier -> modifier.initialize(arena));
        return this;
    }

    public Character performOn(Arena arena) {
        if (!isAlive()) {
            return this; // nothing to do character is dead
        }
        if (currentTarget == null || !currentTarget.isAlive()) {
            currentTarget = findNextLivingTarget(arena);
        }
        if (currentTarget == null) {
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
        return modifiers.stream().noneMatch(modifier -> modifier instanceof Invisibility);
    }

    public Character tryToInflictDamage(Arena arena, AutoAttackAbility ability, AutoAttackDamage autoAttackDamage) {
        // character may evade the attack.
        arena.onEvent(new CharacterBeingHitEvent(arena, this, ability));
        AutoAttackDamage damage = autoAttackDamage;
        for (Modifier modifier : modifiers) {
            if (modifier instanceof ReceivingAutoAttackDamageModifier) {
                autoAttackDamage = ((ReceivingAutoAttackDamageModifier)modifier).modDamage(autoAttackDamage);
            }
        }
        final int healthBefore = currentHealth;
        currentHealth -= Math.max(0, damage.getDamage() - getArmor());
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
        SpellDamage damage = spellDamage;
        for (Modifier modifier : modifiers) {
            if (modifier instanceof ReceivingAutoAttackDamageModifier) {
                damage = ((ReceivingSpellDamageModifier)modifier).modDamage(damage);
            }
        }
        final int healthBefore = currentHealth;
        currentHealth -= Math.max(0, damage.getDamage());
        if (healthBefore > currentHealth) {
            arena.onEvent(new CharacterBeingDamagedEvent(arena, this, damage.getSource(), healthBefore - currentHealth));
        }
        if (currentHealth <= 0) {
            arena.onEvent(new CharacterDiedEvent(arena, this));
        }
        return this;
    }

    public int getArmor() {
        return armor;
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
            "team='" + team + '\'' +
            ", health=" + currentHealth + "/" + maxHealth +
            ", mana=" + currentMana + "/" + maxMana +
            ", armor=" + armor +
            ", magicResistance=" + magicResistance +
            ", abilities=" + abilities +
            ", modifiers=" + modifiers +
            '}';
    }
}
