package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.Disarmed;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This test ensure that {@link Persuasion} is working.
 */
public class PersuasionTest {

    /**
     * In this test two characters are set up against each other in a way, that
     * the ability {@link Persuasion} granting {@link Disarmed} is defining the outcome.
     */
    @Test
    public void testDisarmedModifierPreventsAttacking() {
        Arena arena = new Arena()
            .addCharacter(new Character("A", 70, 0, 0)
                .addAbility(new Persuasion(1.0, 5000)) // 100% chance to disarm the target for 5sec
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
        Assert.assertEquals("number of modifiers on character B", 4, characterB.getModifiers().count());
        Assert.assertEquals("modifiers on character B should be",
            Arrays.asList(Disarmed.class, Disarmed.class, Disarmed.class, Disarmed.class),
            characterB.getModifiers().map(Object::getClass).collect(Collectors.toList()));
    }
}
