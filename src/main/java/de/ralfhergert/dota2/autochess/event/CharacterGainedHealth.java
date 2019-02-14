package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;

/**
 * This event is fired after a {@link Character} gained health.
 */
public class CharacterGainedHealth extends Event {

    private final Character character;
    private final int gainedHealth;

    public CharacterGainedHealth(Arena arena, Character character, int gainedHealth) {
        super(arena);
        this.character = character;
        this.gainedHealth = gainedHealth;
    }

    public Character getCharacter() {
        return character;
    }

    public int getGainedHealth() {
        return gainedHealth;
    }

    @Override
    public String describe() {
        return super.describe() + " " + character.describe() + " gains " + gainedHealth + " points of Mana to a new total of " + character.getCurrentHealth() + " Health";
    }
}
