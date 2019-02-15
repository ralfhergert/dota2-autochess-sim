package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.event.CharacterBeingHitEvent;
import de.ralfhergert.dota2.autochess.event.Event;

/**
 * This ability grants the chance to trigger a damage protection shield when being
 * hit. This ability is granted by {@link de.ralfhergert.dota2.autochess.classes.Knight}.
 */
public class DivineProtection extends Ability implements DamageAbility {

    private final double triggerChance;

    public DivineProtection(double triggerChance) {
        this.triggerChance = triggerChance;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof CharacterBeingHitEvent) {
            CharacterBeingHitEvent beingHitEvent = (CharacterBeingHitEvent)event;
            if (getOwner().equals(beingHitEvent.getCharacter()) &&
                event.getArena().getRandom().nextDouble() < triggerChance) {
                // give the owner a damage reduction shield.
                // TODO needs to be implemented: not sure how long the shield will last, how it works and whether it can be stacked.
                return; // this return statement can be removed when implementation for adding a shield is done.
            }
        }
        super.onEvent(event);
    }
}
