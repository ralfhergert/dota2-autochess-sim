package de.ralfhergert.dota2.autochess.modifier;

public abstract class TimedModifier<Type> implements Modifier<Type> {

    private final long startedAtTick;
    private final long endsAtTick;

    public TimedModifier(long startedAtTick, long endsAtTick) {
        this.startedAtTick = startedAtTick;
        this.endsAtTick = endsAtTick;
    }
}
