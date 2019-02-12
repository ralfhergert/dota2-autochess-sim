package de.ralfhergert.dota2.autochess.modifier;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;

import java.util.Random;
import java.util.function.Supplier;

/**
 * This is the assassin class bonus.
 */
public class CoupDeGrace implements AutoAttackDamageModifier {

    private final double chance;

    private Supplier<Random> randomSupplier = Random::new;

    public CoupDeGrace(double chance) {
        this.chance = chance;
    }

    @Override
    public AutoAttackDamage modify(AutoAttackDamage value) {
        return randomSupplier.get().nextDouble() < chance
            ? new AutoAttackDamage(value.getDamage() * 4, value.getSource())
            : value;
    }

    @Override
    public void initialize(Arena arena) {
        /* in order to seed the random for a deterministic behavior, it is always recommended
         * to use the random provided by the arena. */
        randomSupplier = arena::getRandom;
    }
}
