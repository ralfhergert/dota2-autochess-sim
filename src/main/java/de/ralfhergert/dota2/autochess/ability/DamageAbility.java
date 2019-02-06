package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.character.Character;

public interface DamageAbility {

    int getPotentialDamageVersus(Character character);
}
