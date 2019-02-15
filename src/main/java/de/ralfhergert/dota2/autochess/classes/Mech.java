package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.SelfRepair;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases HP regeneration:
 *  - 2 different Mechs increases HP regeneration by 15 for all friendly Mechs;
 *  - 4 different Mechs increases HP regeneration by additional 25 for all friendly Mechs;
 */
public class Mech extends Ability {

    private static final SelfRepair SELF_REPAIR_2 = new SelfRepair(15);
    private static final SelfRepair SELF_REPAIR_4 = new SelfRepair(25);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> mechClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Mech))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (mechClassesOnTheTeam.size() >= 2) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Mech))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(SELF_REPAIR_2))) {
                        character.addModifier(SELF_REPAIR_2);
                    }
                });
        }
        if (mechClassesOnTheTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Mech))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(SELF_REPAIR_4))) {
                        character.addModifier(SELF_REPAIR_4);
                    }
                });
        }
    }
}
