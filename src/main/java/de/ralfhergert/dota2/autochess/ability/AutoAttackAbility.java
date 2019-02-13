package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackDamageModifier;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackSpeedModifier;
import de.ralfhergert.dota2.autochess.modifier.CanAutoAttackModifier;

import java.util.Random;

/**
 * This auto attack ability lets characters do melee damage.
 */
public class AutoAttackAbility extends Ability implements DamageAbility {

    private int minDamage;
    private int maxDamage;
    private long cooldownMs;
    private int maxRange;
    private long availableOnTick;
    private AttackType attackType = AttackType.Melee;

    public AutoAttackAbility(int minDamage, int maxDamage, long cooldownMs, int maxRange) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.cooldownMs = cooldownMs;
        this.maxRange = maxRange;
    }

    public void initialize(Arena arena) {
        availableOnTick = getNextAvailabilityDelta();
        arena.registerTick(availableOnTick);
    }

    @Override
    public void performOn(Arena arena) {
        if (getOwner().isAlive() &&
            getOwner().getCurrentTarget().isAlive() &&
            getOwner().getCurrentTarget().isVisible() &&
            availableOnTick <= arena.getCurrentTickMs() &&
            getOwner().applyModifiers(true, CanAutoAttackModifier.class)) {
            // try to inflict damage.
            getOwner().getCurrentTarget().tryToInflictDamage(arena, this, getOwner().applyModifiers(new AutoAttackDamage(getPotentialDamage(), getOwner()), AutoAttackDamageModifier.class));
            // calculate and register the next strike.
            availableOnTick = arena.getCurrentTickMs() + getNextAvailabilityDelta();
            arena.registerTick(availableOnTick);
        }
    }

    public long getNextAvailabilityDelta() {
        return getOwner().applyModifiers(cooldownMs, AutoAttackSpeedModifier.class);
    }

    public int getPotentialDamage() {
        return minDamage + (maxDamage > minDamage ? new Random().nextInt(maxDamage - minDamage) : 0);
    }

    public AttackType getAttackType() {
        return attackType;
    }
}
