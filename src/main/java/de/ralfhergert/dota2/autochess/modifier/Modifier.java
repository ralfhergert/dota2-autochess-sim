package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.event.Event;

public interface Modifier<Type> {

    Type modify(Type value);

    default void initialize(Arena arena) {}

    default void onEvent(Event event) {}
}
