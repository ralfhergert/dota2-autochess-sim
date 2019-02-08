package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;

/**
 * This event is fired whenever a {@link Character} evades a hit.
 */
public class CharacterEvadedAttackEvent extends Event {

    private final Character character;
    private final Ability ability;

    public CharacterEvadedAttackEvent(Arena arena, Character character, Ability ability) {
        super(arena);
        this.character = character;
        this.ability = ability;
    }

    public Character getCharacter() {
        return character;
    }

    public Ability getAbility() {
        return ability;
    }

    @Override
    public String describe() {
        return super.describe() + " " + character.describe() + " evaded being hit by " + ability.describe();
    }
}
