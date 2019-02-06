package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;

public interface ReceivingAutoAttackDamageModifier {

    AutoAttackDamage modDamage(AutoAttackDamage current);
}
