package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;

/**
 * This event is fired whenever a {@link Character} gains mana.
 */
public class CharacterGainedMana extends Event {

    private final Character character;
    private final int gainedMana;

    public CharacterGainedMana(Arena arena, Character character, int gainedMana) {
        super(arena);
        this.character = character;
        this.gainedMana = gainedMana;
    }

    public Character getCharacter() {
        return character;
    }

    public int getGainedMana() {
        return gainedMana;
    }

    @Override
    public String describe() {
        return super.describe() + " " + character.describe() + " gains " + gainedMana + " points of Mana to a new total of " + character.getCurrentMana() + " Mana";
    }
}
