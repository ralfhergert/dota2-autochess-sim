package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.ScaleArmor;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases the Magic resistance.
 *  - 2 Naga on board increases the Magic resistance by 20% for all allies;
 *  - 4 Naga on board increases the Magic resistance by additional 40% for all allies;
 */
public class Naga extends Ability {

    private static final ScaleArmor SCALE_ARMOR_2 = new ScaleArmor(0.2);
    private static final ScaleArmor SCALE_ARMOR_4 = new ScaleArmor(0.4);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> nagaClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Naga))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (nagaClassesOnTheTeam.size() >= 2) {
            arena.getAllOfTeam(getOwner().getTeam()).forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(SCALE_ARMOR_2))) {
                    character.addModifier(SCALE_ARMOR_2);
                }
            });
        }
        if (nagaClassesOnTheTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam()).forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(SCALE_ARMOR_4))) {
                    character.addModifier(SCALE_ARMOR_4);
                }
            });
        }
    }
}
