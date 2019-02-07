package de.ralfhergert.dota2.autochess.modifier;

public class IncreaseEvasionByFactorModifier extends Modifier implements ChanceOfBeingHitModifier {

    private final double factor;

    public IncreaseEvasionByFactorModifier(double factor) {
        this(0, Long.MAX_VALUE, factor);
    }

    public IncreaseEvasionByFactorModifier(long startedAtTick, long endsAtTick, double factor) {
        super(startedAtTick, endsAtTick);
        this.factor = factor;
    }

    @Override
    public double modChanceOfBeingHit(double chanceOfBeingHit) {
        return chanceOfBeingHit * (1 - factor);
    }
}
