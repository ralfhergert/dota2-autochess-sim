package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.hero.AntiMage;
import de.ralfhergert.dota2.autochess.hero.ChaosKnight;
import de.ralfhergert.dota2.autochess.hero.Terrorblade;
import de.ralfhergert.dota2.autochess.modifier.FelPower;
import de.ralfhergert.dota2.autochess.species.Demon;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test ensures that the {@link Demon}-species modifier iw working correctly.
 */
public class DemonHunterModifierTest {

    @Test
    public void testDemonHunterModifierRemovesEnemiesFelPower() {
        Arena arena = new Arena()
            .addCharacter(new AntiMage("A", 1))
            .addCharacter(new ChaosKnight("B", 1))
            .initialize();

        Assert.assertEquals("Team B Chaos Knight should not have its Fel Power", 1, arena.getAllOfTeam("B")
            .peek(character -> Assert.assertEquals("number of modifiers", 0, character.getModifiers().count()))
            .count());
    }

    @Test
    public void testOwnDemonHuntersLetsDemonKeepItsFelPower() {
        Arena arena = new Arena()
            .addCharacter(new AntiMage("A", 1))
            .addCharacter(new Terrorblade("B", 1)) // is a Demon and a Demon Hunter
            .addCharacter(new AntiMage("B", 1))
            .initialize();

        Assert.assertEquals("Team B Terror Blade should still have its Fel Power", 1, arena.getAllOfTeam("B")
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Demon))
            .peek(character -> Assert.assertTrue("FelPower is present", character.getModifiers().anyMatch(modifier -> modifier instanceof FelPower)))
            .count());
    }
}
