package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.IncreaseEvasionByFactorModifier;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Increases the evasion for all friendly Elfs.
 *  - 3 Elfs on board increases their evasion by 25% for each of them;
 *  - 6 Elfs on board increases their evasion by further 25% for each of them;
 */
public class Elf extends Ability {

    @Override
    public void initialize(Arena arena) {
        final List<Character> elfsInTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Elf))
            .collect(Collectors.toList());

        if (elfsInTeam.size() >= 3) {
            elfsInTeam.forEach(character -> character.addModifier(new IncreaseEvasionByFactorModifier(0.25)));
        }
        if (elfsInTeam.size() >= 6) {
            elfsInTeam.forEach(character -> character.addModifier(new IncreaseEvasionByFactorModifier(0.25)));
        }
    }
}
