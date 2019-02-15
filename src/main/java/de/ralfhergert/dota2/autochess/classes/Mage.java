package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.MagicResistanceModifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Decrease the enemies magic resistance
 *  - 3 Mages on board decrease the magic resistance of all enemies by 40%;
 *  - 6 Mages on board decrease the magic resistance of all enemies by further 40%;
 */
public class Mage extends Ability {

    private static final MagicResistanceModifier MALUS_FOR_3 = magicResistance -> magicResistance - 0.4;
    private static final MagicResistanceModifier MALUS_FOR_6 = magicResistance -> magicResistance - 0.4;

    @Override
    public void initialize(Arena arena) {
        final Set<Class> mageClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Mage))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (mageClassesOnTheTeam.size() >= 3) {
            arena.getEnemiesOf(getOwner().getTeam())
                .filter(character -> character.getModifiers().noneMatch(modifier -> modifier.equals(MALUS_FOR_3)))
                .forEach(character -> character.addModifier(MALUS_FOR_3));
        }
        if (mageClassesOnTheTeam.size() >= 6) {
            arena.getEnemiesOf(getOwner().getTeam())
                .filter(character -> character.getModifiers().noneMatch(modifier -> modifier.equals(MALUS_FOR_6)))
                .forEach(character -> character.addModifier(MALUS_FOR_6));
        }
    }
}
