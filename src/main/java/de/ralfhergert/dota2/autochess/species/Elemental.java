package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.ability.ElementalResistance;
import de.ralfhergert.dota2.autochess.character.Character;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Species Combo for Elementals:
 *  - 2 different Elementals on board: each elemental has a 30% chance of turning a melee attacker into stone for 3s;
 *  - 4 different Elementals on board: each ally has a 30% chance of turning a melee attacker into stone for 3s;
 */
public class Elemental extends Ability {

    private static final ElementalResistance ELEMENTAL_RESISTANCE = new ElementalResistance(0.3, 3000);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> elementalClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Elemental))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (elementalClassesOnTheTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam()).forEach(character -> {
                if (character.getAbilities().noneMatch(ability -> ability.equals(ELEMENTAL_RESISTANCE))) {
                    character.addAbility(ELEMENTAL_RESISTANCE);
                }
            });
        } else if (elementalClassesOnTheTeam.size() >= 2) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Elemental))
                .forEach(character -> {
                    if (character.getAbilities().noneMatch(ability -> ability.equals(ELEMENTAL_RESISTANCE))) {
                        character.addAbility(ELEMENTAL_RESISTANCE);
                    }
                });
        }
    }
}
