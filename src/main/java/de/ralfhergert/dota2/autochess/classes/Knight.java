package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.ability.DivineProtection;
import de.ralfhergert.dota2.autochess.character.Character;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Grants a chance to get a damage reduction shield.
 *  - 2 Knights on board give a 25% chance to get a shield when being attacked for all friendly knights;
 *  - 4 Knights on board give a 35% chance to get a shield when being attacked for all friendly knights;
 *  - 6 Knights on board give a 45% chance to get a shield when being attacked for all friendly knights;
 */
public class Knight extends Ability {

    private static final DivineProtection BONUS_FOR_2 = new DivineProtection(0.25);
    private static final DivineProtection BONUS_FOR_4 = new DivineProtection(0.35);
    private static final DivineProtection BONUS_FOR_6 = new DivineProtection(0.45);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> knightClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Knight))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (knightClassesOnTheTeam.size() >= 6) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Knight))
                .filter(character -> character.getAbilities().noneMatch(modifier -> modifier.equals(BONUS_FOR_6)))
                .forEach(character -> character.addAbility(BONUS_FOR_6));
        } else if (knightClassesOnTheTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Knight))
                .filter(character -> character.getAbilities().noneMatch(modifier -> modifier.equals(BONUS_FOR_4)))
                .forEach(character -> character.addAbility(BONUS_FOR_4));
        } else if (knightClassesOnTheTeam.size() >= 2) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Knight))
                .filter(character -> character.getAbilities().noneMatch(modifier -> modifier.equals(BONUS_FOR_2)))
                .forEach(character -> character.addAbility(BONUS_FOR_2));
        }
    }
}
