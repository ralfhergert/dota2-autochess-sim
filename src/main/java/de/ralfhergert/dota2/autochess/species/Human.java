package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.ability.Persuasion;
import de.ralfhergert.dota2.autochess.character.Character;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Species Combo for Humans:
 *  - 2 different Humans on board: All friendly humans have a 20% chance of disarming the target for 3s when attacking;
 *  - 4 different Humans on board: All friendly humans have a 25% chance of disarming the target for 3s when attacking;
 *  - 6 different Humans on board: All friendly humans have a 30% chance of disarming the target for 3s when attacking;
 */
public class Human extends Ability {

    private static final Persuasion PERSUASION_2 = new Persuasion(0.2, 3000);
    private static final Persuasion PERSUASION_4 = new Persuasion(0.25, 3000);
    private static final Persuasion PERSUASION_6 = new Persuasion(0.3, 3000);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> humanClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Human))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (humanClassesOnTheTeam.size() >= 2) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Human))
                .forEach(character -> {
                    if (character.getAbilities().noneMatch(ability -> ability.equals(PERSUASION_2))) {
                        character.addAbility(PERSUASION_2);
                    }
                });
        }
        if (humanClassesOnTheTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Human))
                .forEach(character -> {
                    if (character.getAbilities().noneMatch(ability -> ability.equals(PERSUASION_4))) {
                        character.addAbility(PERSUASION_4);
                    }
                });
        }
        if (humanClassesOnTheTeam.size() >= 6) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Human))
                .forEach(character -> {
                    if (character.getAbilities().noneMatch(ability -> ability.equals(PERSUASION_6))) {
                        character.addAbility(PERSUASION_6);
                    }
                });
        }
    }
}
