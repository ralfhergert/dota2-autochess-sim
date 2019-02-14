package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.ArmorModifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Decreases the armor of enemies:
 *  - 2 different Undead classes on board: decreases armor by 5 for all enemies;
 *  - 4 different Undead classes on board: decreases armor by 7 for all enemies;
 */
public class Undead extends Ability {

    private static final ArmorModifier HORROR_2 = armor -> armor - 5;
    private static final ArmorModifier HORROR_4 = armor -> armor - 7;

    @Override
    public void initialize(Arena arena) {
        final Set<Class> undeadClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Undead))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (undeadClassesOnTheTeam.size() >= 2) {
            arena.getEnemiesOf(getOwner().getTeam())
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(HORROR_2))) {
                        character.addModifier(HORROR_2);
                    }
                });
        }
        if (undeadClassesOnTheTeam.size() >= 4) {
            arena.getEnemiesOf(getOwner().getTeam())
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(HORROR_4))) {
                        character.addModifier(HORROR_4);
                    }
                });
        }
    }
}
