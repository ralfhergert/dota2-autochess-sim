package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.classes.Mech;

/**
 * SelfRepair increases the HP regeneration by a given amount.
 * It is granted by {@link Mech}.
 */
public class SelfRepair implements HpRegenerationModifier {

    private final int increase;

    public SelfRepair(int increase) {
        this.increase = increase;
    }

    @Override
    public Integer modify(Integer value) {
        return value + increase;
    }
}
