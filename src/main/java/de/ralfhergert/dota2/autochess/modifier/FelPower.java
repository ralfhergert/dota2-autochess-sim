package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;

/**
 * Fel Power is a {@link de.ralfhergert.dota2.autochess.species.Demon} ability
 * which can be effected by the presence of {@link de.ralfhergert.dota2.autochess.classes.DemonHunter}.
 */
public class FelPower implements AutoAttackDamageModifier {
    @Override
    public AutoAttackDamage modify(AutoAttackDamage value) {
        return new AutoAttackDamage((int)(value.getDamage() * 1.5), value.getSource());
    }
}
