package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.hero.ChaosKnight;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackDamageModifier;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This test ensures that the {@link Demon}-species modifier iw working correctly.
 */
public class DemonModifierTest {

    @Test
    public void testDemonModifierIsAppliedWhenBeingSingle() {
        Arena arena = new Arena()
            .addCharacter(new ChaosKnight("A", 1))
            .initialize();

        List<Character> teamACharacters = arena.getAllOfTeam("A").collect(Collectors.toList());
        Assert.assertEquals("number of characters in team A", 1, teamACharacters.size());
        Assert.assertEquals("Chaos Knight should have one AutoAttackDamageModifier", 1, teamACharacters.get(0).getModifiers()
            .filter(modifier -> modifier instanceof AutoAttackDamageModifier).count());
    }

    @Test
    public void testDemonModifierNotAppliedWhenNotBeingSingle() {
        Arena arena = new Arena()
            .addCharacter(new ChaosKnight("A", 1))
            .addCharacter(new ChaosKnight("A", 1))
            .initialize();

        List<Character> teamACharacters = arena.getAllOfTeam("A").collect(Collectors.toList());
        Assert.assertEquals("number of characters in team A", 2, teamACharacters.size());
        Assert.assertEquals("None of the Chaos Knights should have any AutoAttackDamageModifier", 2,
            teamACharacters.stream()
                .filter(character -> character.getModifiers().noneMatch(modifier -> modifier instanceof AutoAttackDamageModifier))
                .count());
    }
}
