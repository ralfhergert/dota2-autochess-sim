package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.damage.SpellDamage;

public interface ReceivingSpellDamageModifier {

    SpellDamage modDamage(SpellDamage current);
}
