package de.ralfhergert.dota2.autochess.modifier;

/**
 * Nanobots increases the armor and HP regeneration by the same amount.
 */
public class Nanobots implements ArmorModifier, HpRegenerationModifier {

    private final int increase;

    public Nanobots(int increase) {
        this.increase = increase;
    }

    @Override
    public Integer modify(Integer value) {
        return value + increase;
    }
}
