package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.Arena;

/**
 * This modifier disarms its owner, rendering to owner unable to attack.
 * @see de.ralfhergert.dota2.autochess.ability.Persuasion
 */
public class Disarmed implements CanAutoAttackModifier {

    private final long lastUntilTick;

    private Arena arena;

    public Disarmed(long lastUntilTick) {
        this.lastUntilTick = lastUntilTick;
    }

    @Override
    public void initialize(Arena arena) {
        this.arena = arena;
        arena.registerTick(lastUntilTick);
    }

    @Override
    public Boolean modify(Boolean value) {
        return arena.getCurrentTickMs() >= lastUntilTick;
    }
}
