package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.modifier.MaxHealthModifier;

/**
 * Increases the max health by a total of 10%.
 */
public class Ogre extends Ability {

    private static final MaxHealthModifier TWO_HEADS = maxHealth -> (int)(1.1 * maxHealth);

    @Override
    public void initialize(Arena arena) {
        if (getOwner().getModifiers().noneMatch(modifier -> modifier.equals(TWO_HEADS))) {
            getOwner().addModifier(TWO_HEADS);
            // when applied update the current health.
            getOwner().setCurrentHealth(getOwner().getMaxHealth());
        }
    }
}
