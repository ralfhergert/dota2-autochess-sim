package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;

public class Event {

    private final Arena arena;

    public Event(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }
}
