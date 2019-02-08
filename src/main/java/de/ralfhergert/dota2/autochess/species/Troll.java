package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.AutoAttackSpeedModifier;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Increases the attack speed.
 *  - 2 Trolls on board increases the attack speed by 35 for all friendly Trolls;
 *  - 4 Trolls on board increases the attack speed by 35 for all friendly characters instead;
 *
 *  Here the assumption is made that "35" means "35%".
 */
public class Troll extends Ability {

    private static final AutoAttackSpeedModifier SPEED_MODIFIER = new AutoAttackSpeedModifier() {
        @Override
        public Long modify(Long duration) {
            return (long)(duration / 1.35);
        }
    };

    @Override
    public void initialize(Arena arena) {
        final List<Character> trollsInTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Troll))
            .collect(Collectors.toList());

        if (trollsInTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam()).forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(SPEED_MODIFIER))) {
                    character.addModifier(SPEED_MODIFIER);
                }
            });
        } else if (trollsInTeam.size() >= 2) {
            trollsInTeam.forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(SPEED_MODIFIER))) {
                    character.addModifier(SPEED_MODIFIER);
                }
            });
        }
    }
}
