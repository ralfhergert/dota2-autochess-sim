package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.Stone;
import de.ralfhergert.dota2.autochess.species.Elemental;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test ensure that {@link Elemental} is working.
 */
public class ElementalResistanceTest {

    /**
     * In this test two characters are set up against each other in a way,
     * that the ability ElementalResistance is defining the outcome.
     */
    @Test
    public void testStoneModifierPreventsAttacking() {
        Arena arena = new Arena()
            .addCharacter(new Character("A", 70, 0, 0)
                .addAbility(new ElementalResistance(1.0, 5000)) // 100% chance to freeze the attacker for 5sec
                .addAbility(new AutoAttackAbility(50, 50, 1000, 205))
            )
            .addCharacter(new Character("B", 200, 0, 0)
                .addAbility(new AutoAttackAbility(50, 50, 1000, 205))
            )
            .initialize();

        while (!arena.isFightOver()) {
            arena.step();
        }

        Assert.assertEquals("winning team is", "A", arena.getWinningTeam());
        Character characterB = arena.getAllOfTeam("B").findFirst().orElseGet(() -> {
            Assert.fail("character B wasn't found");
            return null;
        });
        Assert.assertEquals("number of modifiers on character B", 1, characterB.getModifiers().count());
        Assert.assertEquals("modifier on character B", Stone.class, characterB.getModifiers().findFirst().get().getClass());
    }
}
