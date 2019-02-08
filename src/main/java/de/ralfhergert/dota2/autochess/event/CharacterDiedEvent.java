package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;

/**
 * This event is fired when a {@link Character} died on board.
 */
public class CharacterDiedEvent extends Event {

    private final Character character;

    public CharacterDiedEvent(Arena arena, Character character) {
        super(arena);
        this.character = character;
    }

    @Override
    public String describe() {
        return super.describe() + " " + character.describe() + " died";
    }
}
