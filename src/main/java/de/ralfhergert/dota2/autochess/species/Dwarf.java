package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackRangeModifier;

/**
 * Increases the attack range of the owner by 300.
 */
public class Dwarf extends Ability {

    private static final AutoAttackRangeModifier RANGE_MODIFIER = range -> range + 300;

    @Override
    public void initialize(Arena arena) {
        if (getOwner().getModifiers().noneMatch(modifier -> modifier.equals(RANGE_MODIFIER))) {
            getOwner().addModifier(RANGE_MODIFIER);
        }
    }
}
