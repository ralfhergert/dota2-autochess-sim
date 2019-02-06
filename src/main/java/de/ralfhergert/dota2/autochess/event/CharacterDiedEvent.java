package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;

public class CharacterDiedEvent extends Event {

    private final Character character;

    public CharacterDiedEvent(Arena arena, Character character) {
        super(arena);
        this.character = character;
    }
}
