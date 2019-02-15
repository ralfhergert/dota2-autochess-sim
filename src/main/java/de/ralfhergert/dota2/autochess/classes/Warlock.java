package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.ability.LifeSteal;
import de.ralfhergert.dota2.autochess.character.Character;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides Life Steal to all allies:
 *  - 3 Warlocks on board provides a 20% life steal to all allies;
 *  - 6 Warlocks on board provides an additional 30% life steal to all allies;
 */
public class Warlock extends Ability {

    private static final LifeSteal BONUS_FOR_3 = new LifeSteal(0.2);
    private static final LifeSteal BONUS_FOR_6 = new LifeSteal(0.3);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> warlockClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Warlock))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (warlockClassesOnTheTeam.size() >= 3) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().noneMatch(modifier -> modifier.equals(BONUS_FOR_3)))
                .forEach(character -> character.addAbility(BONUS_FOR_3));
        }
        if (warlockClassesOnTheTeam.size() >= 6) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().noneMatch(modifier -> modifier.equals(BONUS_FOR_6)))
                .forEach(character -> character.addAbility(BONUS_FOR_6));
        }
    }
}
