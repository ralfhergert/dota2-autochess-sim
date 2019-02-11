package de.ralfhergert.dota2.autochess.species;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.modifier.FactorAutoAttackDamageModifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Increases the attack damage:
 *  - 2 Beasts: increases the attack damage by 10% for all allies.
 *  - 4 Beasts: increases the attack damage by 15% for all allies.
 *  - 6 Beasts: increases the attack damage by 20% for all allies.
 */
public class Beast extends Ability {

    private static final FactorAutoAttackDamageModifier BONUS_FOR_2 = new FactorAutoAttackDamageModifier(1.1);
    private static final FactorAutoAttackDamageModifier BONUS_FOR_4 = new FactorAutoAttackDamageModifier(1.15);
    private static final FactorAutoAttackDamageModifier BONUS_FOR_6 = new FactorAutoAttackDamageModifier(1.20);

    @Override
    public void initialize(Arena arena) {
        final Set<Class> beastClassesOnTheTeam = arena.getAllOfTeam(getOwner().getTeam())
            .filter(character -> character.getAbilities().anyMatch(ability -> ability instanceof Beast))
            .map(Character::getClass)
            .collect(Collectors.toSet());

        if (beastClassesOnTheTeam.size() >= 2) {
            arena.getAllOfTeam(getOwner().getTeam()).forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_2))) {
                    character.addModifier(BONUS_FOR_2);
                }
            });
        }
        if (beastClassesOnTheTeam.size() >= 4) {
            arena.getAllOfTeam(getOwner().getTeam()).forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_4))) {
                    character.addModifier(BONUS_FOR_4);
                }
            });
        }
        if (beastClassesOnTheTeam.size() >= 6) {
            arena.getAllOfTeam(getOwner().getTeam()).forEach(character -> {
                if (character.getModifiers().noneMatch(modifier -> modifier.equals(BONUS_FOR_6))) {
                    character.addModifier(BONUS_FOR_6);
                }
            });
        }
    }
}
