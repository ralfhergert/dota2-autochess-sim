package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.classes.DemonHunter;
import de.ralfhergert.dota2.autochess.modifier.FelPower;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Being a Demon this ability grants the character a 50% damage bonus if it is
 * the only demon on the team. This bonus is also influenced by the presence of
 * {@link DemonHunter}s:
 *  - if there at least one Demon Hunter on the enemy team, this bonus is not
 *    applied, unless your team has two different Demon Hunter classes on its own.
 */
public class Demon extends Ability {

    @Override
    public void initialize(Arena arena) {
        final Set<Class<?>> demonHunterClassesOnTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof DemonHunter))
            .map(Character::getClass)
            .collect(Collectors.toSet());
        final Set<Class<?>> demonHunterClassesOnEnemyTeam = arena.getEnemiesOf(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof DemonHunter))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (!demonHunterClassesOnEnemyTeam.isEmpty() && demonHunterClassesOnTeam.size() < 2) {
            return; // enemy demon hunter denies Fel Power.
        }

        final boolean onlyDemonOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Demon))
            .count() == 1;

        if (onlyDemonOnTheTeam) {
            getOwner().addModifier(new FelPower());
        }
    }
}
