package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.MaxHealthModifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases the max health:
 *  - 2 different Orc classes on board: increases by 250 for all friendly Orcs;
 *  - 4 different Orc classes on board: increases by additional 350 for all friendly Orcs;
 */
public class Orc extends Ability {

    private static final MaxHealthModifier BATTLE_HARDENED_2 = maxHealth -> maxHealth + 250;
    private static final MaxHealthModifier BATTLE_HARDENED_4 = maxHealth -> maxHealth + 350;

    @Override
    public void initialize(Arena arena) {
        final Set<Class> orcClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Orc))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (orcClassesOnTheTeam.size() >= 2) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Orc))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(BATTLE_HARDENED_2))) {
                        character.addModifier(BATTLE_HARDENED_2);
                        // when applied update the current health.
                        character.setCurrentHealth(getOwner().getMaxHealth());
                    }
                });
        }
        if (orcClassesOnTheTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Orc))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(BATTLE_HARDENED_4))) {
                        character.addModifier(BATTLE_HARDENED_4);
                        // when applied update the current health.
                        character.setCurrentHealth(getOwner().getMaxHealth());
                    }
                });
        }
    }
}
