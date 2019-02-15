package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.ArmorModifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases the armor.
 *  - 3 Warriors on board increase armor by 7 for all friendly warriors;
 *  - 6 Warriors on board increase armor by additional 8 for all friendly warriors;
 *  - 9 Warriors on board increase armor by additional 9 for all friendly warriors;
 */
public class Warrior extends Ability {

    private static final ArmorModifier BONUS_FOR_3 = armor -> armor + 7;
    private static final ArmorModifier BONUS_FOR_6 = armor -> armor + 8;
    private static final ArmorModifier BONUS_FOR_9 = armor -> armor + 9;

    @Override
    public void initialize(Arena arena) {
        final Set<Class> warriorClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Warrior))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (warriorClassesOnTheTeam.size() >= 3) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Warrior))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_3))) {
                        character.addModifier(BONUS_FOR_3);
                    }
                });
        }
        if (warriorClassesOnTheTeam.size() >= 6) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Warrior))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_6))) {
                        character.addModifier(BONUS_FOR_6);
                    }
                });
        }
        if (warriorClassesOnTheTeam.size() >= 9) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Warrior))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_9))) {
                        character.addModifier(BONUS_FOR_9);
                    }
                });
        }
    }
}
