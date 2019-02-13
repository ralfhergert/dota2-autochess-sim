package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.Arena;

public class Stone implements CanAutoAttackModifier, CanMoveModifier {

    private final long lastUntilTick;

    private Arena arena;

    public Stone(long lastUntilTick) {
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
