package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;

public class CharacterBeingDamagedEvent extends Event {

    private final Character character;
    private final Character attacker;
    private final int damage;

    public CharacterBeingDamagedEvent(Arena arena, Character character, Character attacker, int damage) {
        super(arena);
        this.character = character;
        this.attacker = attacker;
        this.damage = damage;
    }

    public Character getCharacter() {
        return character;
    }

    public Character getAttacker() {
        return attacker;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String describe() {
        return super.describe() + " " + character.describe() + " is taken " + damage + " points damage from " + attacker.describe() + " to a new total of " + character.getCurrentHealth() + " HP";
    }
}
