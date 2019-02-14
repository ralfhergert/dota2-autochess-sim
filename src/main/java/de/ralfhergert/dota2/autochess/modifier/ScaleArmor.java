package de.ralfhergert.dota2.autochess.modifier;

/**
 * Scale Armor increase the magic resistance of its owner.
 */
public class ScaleArmor implements MagicResistanceModifier {

    private final double increase;

    public ScaleArmor(double increase) {
        this.increase = increase;
    }

    @Override
    public Double modify(Double value) {
        return value + increase;
    }
}
