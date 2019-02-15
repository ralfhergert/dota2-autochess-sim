package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackDamageModifier;
import de.ralfhergert.dota2.autochess.modifier.CoupDeGrace;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Adds a chance for critical damage.
 *  - 3 Assassins on board grants all assassins a +10% to deal 4x damage;
 *  - 6 Assassins on board grants all assassins a +20% to deal 4x damage;
 */
public class Assassin extends Ability {

    private static final AutoAttackDamageModifier BONUS_FOR_3 = new CoupDeGrace(0.1);
    private static final AutoAttackDamageModifier BONUS_FOR_6 = new CoupDeGrace(0.3);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> assassinClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Assassin))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        final List<Character> assassinsInTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Assassin))
            .collect(Collectors.toList());

        if (assassinClassesOnTheTeam.size() >= 6) {
            assassinsInTeam.forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_6))) {
                    character.addModifier(BONUS_FOR_6);
                }
            });
        } else if (assassinClassesOnTheTeam.size() >= 3) {
            assassinsInTeam.forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_3))) {
                    character.addModifier(BONUS_FOR_3);
                }
            });
        }
    }
}
