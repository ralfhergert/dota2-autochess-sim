package de.ralfhergert.dota2.autochess.ability;

import org.junit.Assert;
import org.junit.Test;

/**
 * This test ensures that {@link AutoAttackAbility} is working correctly.
 */
public class AutoAttackAbilityTest {

    @Test
    public void testMinAndMaxDamageCanBeTheSame() {
        AutoAttackAbility ability = new AutoAttackAbility(10, 10, 1000, 200);
        Assert.assertEquals("potential damage is", 10, ability.getPotentialDamage());
    }
}
