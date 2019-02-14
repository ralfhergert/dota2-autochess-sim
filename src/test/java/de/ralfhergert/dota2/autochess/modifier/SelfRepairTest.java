package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.character.Character;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test ensure that {@link SelfRepair} is working.
 */
public class SelfRepairTest {

    /**
     * In this test two characters are set up against each other in a way, that
     * the modifier {@link SelfRepair} is defining the outcome.
     */
    @Test
    public void testSelfRepairKeepsAFromDying() {
        Arena arena = new Arena()
            .addCharacter(new Character("A", 70, 0, 0)
                .addAbility(new AutoAttackAbility(50, 50, 1000, 205))
                .addModifier(new SelfRepair(100))
            )
            .addCharacter(new Character("B", 200, 0, 0)
                .addAbility(new AutoAttackAbility(50, 50, 1000, 205))
            )
            .initialize();

        while (!arena.isFightOver()) {
            arena.step();
        }

        Assert.assertEquals("winning team is", "A", arena.getWinningTeam());
        Character characterA = arena.getAllOfTeam("A").findFirst().orElseGet(() -> {
            Assert.fail("character A wasn't found");
            return null;
        });
        Assert.assertEquals("health of A", 70, characterA.getCurrentHealth());
    }
}
