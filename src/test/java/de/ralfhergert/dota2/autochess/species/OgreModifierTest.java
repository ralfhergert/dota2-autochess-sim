package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * This test ensures that the {@link Ogre}-species modifier is working correctly.
 */
public class OgreModifierTest {

    @Test
    public void testTripleElfModifier() {
        Arena arena = new Arena()
            .addCharacter(new Character("A", 100, 0, 0)
                .addAbility(new Ogre()))
            .initialize();

        Optional<Character> ogre = arena.getAllOfTeam("A").findFirst();
        Assert.assertTrue("ogre should be in arena", ogre.isPresent());
        Assert.assertEquals("ogre should have maximum health", 110, ogre.get().getMaxHealth());
        Assert.assertEquals("ogre should have current health", 110, ogre.get().getCurrentHealth());
    }
}
