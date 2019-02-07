package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.hero.AntiMage;
import de.ralfhergert.dota2.autochess.hero.BatRider;
import de.ralfhergert.dota2.autochess.modifier.ChanceOfBeingHitModifier;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This test ensures that the {@link Elf}-species modifier iw working correctly.
 */
public class ElfModifierTest {

    @Test
    public void testTripleElfModifier() {
        Arena arena = new Arena()
            .addCharacter(new AntiMage("A", 1))
            .addCharacter(new AntiMage("A", 1))
            .addCharacter(new AntiMage("A", 1))
            .addCharacter(new BatRider("A", 1))
            .addCharacter(new AntiMage("B", 1))
            .initialize();

        List<Character> teamACharacters = arena.getAllOfTeam("A").collect(Collectors.toList());
        Assert.assertEquals("number of characters in team A", 4, teamACharacters.size());
        Assert.assertEquals("number of AntiMages in team A", 3, arena.getAllOfTeam("A")
            .filter(character -> character instanceof AntiMage)
            .peek(character -> Assert.assertEquals("AnitMage should have one ChanceOfBeingHitModifier", 1,
                character.getModifiers().filter(modifier -> modifier instanceof ChanceOfBeingHitModifier).count()))
            .count());
        Assert.assertEquals("number of BatRider in team A", 1, arena.getAllOfTeam("A")
            .filter(character -> character instanceof BatRider)
            .peek(character -> Assert.assertTrue("has no ChanceOfBeingHitModifier",
                character.getModifiers().noneMatch(modifier -> modifier instanceof ChanceOfBeingHitModifier)))
            .count());
    }
}
