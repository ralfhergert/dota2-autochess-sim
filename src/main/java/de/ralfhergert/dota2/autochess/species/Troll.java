package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackSpeedModifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases the attack speed.
 *  - 2 Trolls on board increases the attack speed by 35 for all friendly Trolls;
 *  - 4 Trolls on board increases the attack speed by 35 for all friendly characters instead;
 *
 *  Here the assumption is made that "35" means "35%".
 */
public class Troll extends Ability {

    private static final AutoAttackSpeedModifier SPEED_MODIFIER = duration -> (long)(duration / 1.35);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> trollClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Troll))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (trollClassesOnTheTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam()).forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(SPEED_MODIFIER))) {
                    character.addModifier(SPEED_MODIFIER);
                }
            });
        } else if (trollClassesOnTheTeam.size() >= 2) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Troll))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(SPEED_MODIFIER))) {
                        character.addModifier(SPEED_MODIFIER);
                    }
                });
        }
    }
}
