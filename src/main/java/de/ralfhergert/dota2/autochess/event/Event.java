package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;

/**
 * This is the super-class of all events.
 */
public class Event {

    private final Arena arena;

    public Event(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }

    public String describe() {
        return String.format("at %1$d.%2$ds", arena.getCurrentTickMs() / 1000, arena.getCurrentTickMs() % 1000);
    }
}
