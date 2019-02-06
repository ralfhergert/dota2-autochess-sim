package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.event.Event;

public class Modifier {

    private long startedAtTick;
    private long endsAtTick;

    public Modifier initialize(Arena arena) {
        return this;
    }

    public Modifier onEvent(Event event) {
        return this;
    }
}
