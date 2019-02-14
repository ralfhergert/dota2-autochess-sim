package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.event.CharacterBeingHitEvent;
import de.ralfhergert.dota2.autochess.event.Event;
import de.ralfhergert.dota2.autochess.modifier.Disarmed;

/**
 * When the owner of this ability attacks, this modifier grants a chance
 * to disarm the target for 3sec.
 */
public class Persuasion extends Ability {

    private final double chance;
    private final long durationMs;

    public Persuasion(double chance, long durationMs) {
        this.chance = chance;
        this.durationMs = durationMs;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof CharacterBeingHitEvent) {
            CharacterBeingHitEvent characterBeingHitEvent = (CharacterBeingHitEvent)event;
            if (characterBeingHitEvent.getAbility() instanceof AutoAttackAbility &&
                characterBeingHitEvent.getAbility().getOwner().equals(getOwner()) &&
                event.getArena().getRandom().nextDouble() < chance) {
                characterBeingHitEvent.getCharacter().addModifier(new Disarmed(event.getArena().getCurrentTickMs() + durationMs));
            }
        }
    }
}
