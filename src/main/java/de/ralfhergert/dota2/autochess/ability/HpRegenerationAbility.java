package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.modifier.HpRegenerationModifier;

/**
 * This auto attack ability lets characters do melee damage.
 */
public class HpRegenerationAbility extends Ability implements DamageAbility {

    private final int baseRegeneration;

    private long availableOnTick;

    public HpRegenerationAbility(int baseRegeneration) {
        this.baseRegeneration = baseRegeneration;
    }

    @Override
    public void initialize(Arena arena) {
        availableOnTick = arena.getCurrentTickMs() + 1000;
        arena.registerTick(availableOnTick);
    }

    @Override
    public void performOn(Arena arena) {
        if (getOwner().isAlive() &&
            availableOnTick <= arena.getCurrentTickMs()) {
            getOwner().setCurrentHealth(getOwner().getCurrentHealth() + getOwner().applyModifiers(baseRegeneration, HpRegenerationModifier.class));
            // calculate and register the next regeneration.
            availableOnTick = arena.getCurrentTickMs() + 1000;
            arena.registerTick(availableOnTick);
        }
    }
}
