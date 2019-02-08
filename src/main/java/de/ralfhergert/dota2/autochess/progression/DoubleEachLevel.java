package de.ralfhergert.dota2.autochess.progression;

/**
 * This class implements to double a value for each level.
 * Starting level is 1 at which the value is kept as it is.
 * Equation is: value * (2^(level-1))
 */
public class DoubleEachLevel {

    private final int factor;

    public DoubleEachLevel(int level) {
        this.factor = (int)Math.pow(2.0, level - 1.0);
    }

    public int of(int value) {
        return value * factor;
    }
}
