package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.hero.Viper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This test ensures that the {@link Demon}-species modifier iw working correctly.
 */
public class DragonModifierTest {

    @Test
    public void testDragonModifierIsNotAppliedForTwoDragons() {
        Arena arena = new Arena()
            .addCharacter(new Viper("A", 1))
            .addCharacter(new Viper("A", 1))
            .initialize();

        List<Character> teamACharacters = arena.getAllOfTeam("A").collect(Collectors.toList());
        Assert.assertEquals("number of characters in team A", 2, teamACharacters.size());
        Assert.assertEquals("All Vipers should start with 0 Mana", 2, teamACharacters.stream()
            .peek(viper -> Assert.assertEquals("current mana is", 0, viper.getCurrentMana()))
            .count());
    }

    @Test
    public void testDragonModifierIsAppliedForThreeDragons() {
        Arena arena = new Arena()
            .addCharacter(new Viper("A", 1))
            .addCharacter(new Viper("A", 1))
            .addCharacter(new Viper("A", 1))
            .initialize();

        List<Character> teamACharacters = arena.getAllOfTeam("A").collect(Collectors.toList());
        Assert.assertEquals("number of characters in team A", 3, teamACharacters.size());
        Assert.assertEquals("All Vipers should start with 100 Mana", 3, teamACharacters.stream()
            .peek(viper -> Assert.assertEquals("current mana is", 100, viper.getCurrentMana()))
            .count());
    }
}
