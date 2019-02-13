package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.Nanobots;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases armor and HP regeneration:
 *  - 3 different Goblins increases armor and HP regernation by 15 for one random ally;
 *  - 6 different Goblins increases armor and HP regernation by 15 for all friendly Goblins;
 */
public class Goblin extends Ability {

    private static final Nanobots NANOBOTS = new Nanobots(15);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> goblinClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Goblin))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (goblinClassesOnTheTeam.size() >= 6) {
            arena.getAllOfTeam(getOwner().getTeam())
                .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Goblin))
                .forEach(character -> {
                    if (character.getModifiers().noneMatch(modifier -> modifier.equals(NANOBOTS))) {
                        character.addModifier(NANOBOTS);
                    }
                });
        } else if (goblinClassesOnTheTeam.size() >= 3 &&
            arena.getAllOfTeam(getOwner().getTeam()) // none of the allies has yet NANOBOTS
                .noneMatch(character -> character.getModifiers().anyMatch(modifier -> modifier.equals(NANOBOTS)))) {
            // pick one of the allies by random.
            List<Character> allies = arena.getAllOfTeam(getOwner().getTeam()).collect(Collectors.toList());
            allies.get(arena.getRandom().nextInt(allies.size())).addModifier(NANOBOTS);
        }
    }
}
