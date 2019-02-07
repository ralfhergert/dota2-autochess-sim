package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackDamageModifier;
import de.ralfhergert.dota2.autochess.species.Demon;

/**
 * Being a Demon Hunter grants the character a 50% damage bonus if there is a demon on
 * the enemy team.
 */
public class DemonHunter extends Ability {

    @Override
    public void initialize(Arena arena) {
        final boolean demonOnTheEnemyTeam = arena.getEnemiesOf(getOwner().getTeam())
            .anyMatch(character -> character.getAbilities().anyMatch(ability -> ability instanceof Demon));

        if (demonOnTheEnemyTeam) {
            getOwner().addModifier(new AutoAttackDamageModifier() {
                @Override
                public AutoAttackDamage modify(AutoAttackDamage value) {
                    return new AutoAttackDamage((int)(value.getDamage() * 1.5), value.getSource());
                }
            });
        }
    }
}
