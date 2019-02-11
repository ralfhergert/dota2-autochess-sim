package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;

/**
 * This modifier uses a factor to scale the auto attack damage up (or down).
 */
public class FactorAutoAttackDamageModifier implements AutoAttackDamageModifier {

    private final double factor;

    public FactorAutoAttackDamageModifier(double factor) {
        this.factor = factor;
    }

    @Override
    public AutoAttackDamage modify(AutoAttackDamage value) {
        return new AutoAttackDamage((int)(value.getDamage() * factor), value.getSource());
    }
}
