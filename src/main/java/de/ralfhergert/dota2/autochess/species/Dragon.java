package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Being a Dragon this ability grants the character 100 Mana when the battle
 * starts if there a 3 or more dragon on the team.
 */
public class Dragon extends Ability {

    @Override
    public void initialize(Arena arena) {
        final List<Character> dragonsOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Dragon))
            .collect(Collectors.toList());

        if (dragonsOnTheTeam.size() >= 3) {
            dragonsOnTheTeam.forEach(dragon -> dragon.setCurrentMana(100));
        }
    }
}
