package de.ralfhergert.dota2.autochess.modifier;

/**
 * This modifier can define a characters visibility.
 */
public class VisibilityModifier extends TimedModifier<Boolean> {

    private final boolean isVisible;

    public VisibilityModifier(boolean isVisible) {
        this(0, Long.MAX_VALUE, isVisible);
    }

    public VisibilityModifier(long startedAtTick, long endsAtTick, boolean isVisible) {
        super(startedAtTick, endsAtTick);
        this.isVisible = isVisible;
    }

    @Override
    public Boolean modify(Boolean visible) {
        return isVisible;
    }
}
