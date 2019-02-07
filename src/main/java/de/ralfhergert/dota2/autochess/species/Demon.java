package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackDamageModifier;

/**
 * Being a Demon this ability grants the character a 50% damage bonus if it is
 * the only demon on the team.
 */
public class Demon extends Ability {

    @Override
    public void initialize(Arena arena) {
        final boolean onlyDemonOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Demon))
            .count() == 1;

        if (onlyDemonOnTheTeam) {
            getOwner().addModifier(new AutoAttackDamageModifier() {
                @Override
                public AutoAttackDamage modify(AutoAttackDamage value) {
                    return new AutoAttackDamage((int)(value.getDamage() * 1.5), value.getSource());
                }
            });
        }
    }
}
