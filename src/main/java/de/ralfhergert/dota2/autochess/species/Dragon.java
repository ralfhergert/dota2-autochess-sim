package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Being a Dragon this ability grants the character 100 Mana when the battle
 * starts if there are 3 or more different dragons on the team.
 */
public class Dragon extends Ability {

    @Override
    public void initialize(Arena arena) {
        final Set<Class> dragonsClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Dragon))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (dragonsClassesOnTheTeam.size() >= 3) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Dragon))
                .forEach(dragon -> dragon.setCurrentMana(100));
        }
    }
}
