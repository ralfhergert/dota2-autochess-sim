package de.ralfhergert.dota2.autochess.modifier;

/**
 * When this modifier is asked whether its owner is visible it always replies false.
 */
public class Invisibility extends TimedModifier<Boolean> {

    public Invisibility(long startedAtTick, long endsAtTick) {
        super(startedAtTick, endsAtTick);
    }

    @Override
    public Boolean modify(Boolean visible) {
        return false;
    }
}
