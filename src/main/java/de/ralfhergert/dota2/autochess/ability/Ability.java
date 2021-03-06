package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.event.Event;

/**
 * This is the super-class for all abilities.
 */
public class Ability {

    private Character owner;

    public Character getOwner() {
        return owner;
    }

    public void setOwner(Character owner) {
        this.owner = owner;
    }

    public void initialize(Arena arena) {}

    public void performOn(Arena arena) {}

    public void onEvent(Event event) {}

    public String describe() {
        return "(" + getClass().getSimpleName() + " from " + owner.describe() + ")";
    }
}
