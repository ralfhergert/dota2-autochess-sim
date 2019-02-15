package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.damage.AutoAttackDamage;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackDamageModifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases the attack damage:
 *  - 3 different Hunter classes on board: increases attack damage for 25% for all friendly hunters;
 *  - 6 different Hunter classes on board: increases attack damage for additional 35% for all friendly hunters;
 */
public class Hunter extends Ability {

    private static final AutoAttackDamageModifier BONUS_FOR_3 = damage -> new AutoAttackDamage((int)(damage.getDamage() * 0.25), damage.getSource());
    private static final AutoAttackDamageModifier BONUS_FOR_6 = damage -> new AutoAttackDamage((int)(damage.getDamage() * 0.35), damage.getSource());

    @Override
    public void initialize(Arena arena) {
        final Set<Class> hunterClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Hunter))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (hunterClassesOnTheTeam.size() >= 3) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Hunter))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_3))) {
                        character.addModifier(BONUS_FOR_3);
                    }
                });
        }
        if (hunterClassesOnTheTeam.size() >= 6) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Hunter))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_6))) {
                        character.addModifier(BONUS_FOR_6);
                    }
                });
        }
    }
}
