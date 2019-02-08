package de.ralfhergert.dota2.autochess.modifier;

/**
 * This class may used for temporal modifications, which die-off after some time.
 * @param <Type> value type this modifier does effect.
 */
public abstract class TimedModifier<Type> implements Modifier<Type> {

    private final long startedAtTick;
    private final long endsAtTick;

    public TimedModifier(long startedAtTick, long endsAtTick) {
        this.startedAtTick = startedAtTick;
        this.endsAtTick = endsAtTick;
    }
}
