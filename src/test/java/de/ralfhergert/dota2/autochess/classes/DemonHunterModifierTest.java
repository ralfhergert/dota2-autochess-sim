package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.hero.AntiMage;
import de.ralfhergert.dota2.autochess.hero.BatRider;
import de.ralfhergert.dota2.autochess.hero.ChaosKnight;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackDamageModifier;
import de.ralfhergert.dota2.autochess.species.Demon;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This test ensures that the {@link Demon}-species modifier iw working correctly.
 */
public class DemonHunterModifierTest {

    @Test
    public void testDemonHunterModifierIsAppliedWhenEnemyHasADemon() {
        Arena arena = new Arena()
            .addCharacter(new AntiMage("A", 1))
            .addCharacter(new ChaosKnight("B", 1))
            .initialize();

        List<Character> teamACharacters = arena.getAllOfTeam("A").collect(Collectors.toList());
        Assert.assertEquals("number of characters in team A", 1, teamACharacters.size());
        Assert.assertEquals("Anit Mage should have one AutoAttackDamageModifier", 1, teamACharacters.get(0).getModifiers()
            .filter(modifier -> modifier instanceof AutoAttackDamageModifier).count());
    }

    @Test
    public void testDemonHunterModifierIsNotAppliedWhenEnemyHasNoDemon() {
        Arena arena = new Arena()
            .addCharacter(new AntiMage("A", 1))
            .addCharacter(new BatRider("B", 1))
            .initialize();

        List<Character> teamACharacters = arena.getAllOfTeam("A").collect(Collectors.toList());
        Assert.assertEquals("number of characters in team A", 1, teamACharacters.size());
        Assert.assertEquals("Anti Mage should not have any AutoAttackDamageModifiers", 1,
            teamACharacters.stream()
                .filter(character -> character.getModifiers().noneMatch(modifier -> modifier instanceof AutoAttackDamageModifier))
                .count());
    }
}
