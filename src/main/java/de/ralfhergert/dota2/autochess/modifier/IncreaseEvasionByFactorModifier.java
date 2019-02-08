package de.ralfhergert.dota2.autochess.modifier;

/**
 * This modifier effects the "chance of being hit".
 */
public class IncreaseEvasionByFactorModifier extends TimedModifier<Double> implements ChanceOfBeingHitModifier {

    private final double factor;

    public IncreaseEvasionByFactorModifier(double factor) {
        this(0, Long.MAX_VALUE, factor);
    }

    public IncreaseEvasionByFactorModifier(long startedAtTick, long endsAtTick, double factor) {
        super(startedAtTick, endsAtTick);
        this.factor = factor;
    }

    @Override
    public Double modify(Double chanceOfBeingHit) {
        return chanceOfBeingHit * (1 - factor);
    }
}
