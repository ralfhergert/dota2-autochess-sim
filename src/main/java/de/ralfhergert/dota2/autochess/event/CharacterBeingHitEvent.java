package de.ralfhergert.dota2.autochess.event;

import de.ralfhergert.dota2.autochess.Arena;
import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.character.Character;

public class CharacterBeingHitEvent extends Event {

    private final Character character;
    private final Ability ability;

    public CharacterBeingHitEvent(Arena arena, Character character, Ability ability) {
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
}
