package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.event.Event;

/**
 * This is the super-class of all Modifiers.
 * @param <Type> value type this modifier does effect.
 */
public interface Modifier<Type> {

    Type modify(Type value);

    default void initialize(Arena arena) {}

    default void onEvent(Event event) {}
}
