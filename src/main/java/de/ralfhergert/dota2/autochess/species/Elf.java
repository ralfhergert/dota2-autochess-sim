package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.IncreaseEvasionByFactorModifier;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases the evasion for all friendly Elfs.
 *  - 3 Elfs on board increases their evasion by 25% for each of them;
 *  - 6 Elfs on board increases their evasion by further 25% for each of them;
 */
public class Elf extends Ability {

    private static final IncreaseEvasionByFactorModifier BONUS_FOR_3 = new IncreaseEvasionByFactorModifier(0.25);
    private static final IncreaseEvasionByFactorModifier BONUS_FOR_6 = new IncreaseEvasionByFactorModifier(0.25);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> elfClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Elf))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        final List<Character> elfsInTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Elf))
            .collect(Collectors.toList());

        if (elfClassesOnTheTeam.size() >= 3) {
            elfsInTeam.forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_3))) {
                    character.addModifier(BONUS_FOR_3);
                }
            });
        }
        if (elfClassesOnTheTeam.size() >= 6) {
            elfsInTeam.forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_6))) {
                    character.addModifier(BONUS_FOR_6);
                }
            });
        }
    }
}
