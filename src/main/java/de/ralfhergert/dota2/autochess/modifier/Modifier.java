package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.event.Event;

public class Modifier {

    private final long startedAtTick;
    private final long endsAtTick;

    public Modifier(long startedAtTick, long endsAtTick) {
        this.startedAtTick = startedAtTick;
        this.endsAtTick = endsAtTick;
    }

    public Modifier initialize(Arena arena) {
        return this;
    }

    public Modifier onEvent(Event event) {
        return this;
    }
}
