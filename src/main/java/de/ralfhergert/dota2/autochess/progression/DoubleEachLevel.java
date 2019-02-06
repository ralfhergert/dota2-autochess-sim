package de.ralfhergert.dota2.autochess.progression;

public class DoubleEachLevel {

    private final int factor;

    public DoubleEachLevel(int level) {
        this.factor = (int)Math.pow(2.0, level - 1.0);
    }

    public int of(int value) {
        return value * factor;
    }
}
