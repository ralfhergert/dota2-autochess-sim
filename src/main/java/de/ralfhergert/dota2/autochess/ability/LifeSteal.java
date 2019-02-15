package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.event.CharacterBeingDamagedEvent;
import de.ralfhergert.dota2.autochess.event.CharacterGainedHealth;
import de.ralfhergert.dota2.autochess.event.Event;

/**
 * LifeSteal lets the owner of this ability gain life, whenever the owner inflicted
 * damage on a target. The amount for life gained is calculated as a percentage of
 * the inflicted damage.
 */
public class LifeSteal extends Ability {

    private final double percentage;

    public LifeSteal(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof CharacterBeingDamagedEvent) {
            CharacterBeingDamagedEvent beingDamagedEvent = (CharacterBeingDamagedEvent)event;
            if (getOwner().equals(beingDamagedEvent.getAttacker())) {
                final int life = (int)(beingDamagedEvent.getDamage() * percentage);
                final int healthBefore = getOwner().getCurrentHealth();
                final int healthAfter = Math.min(getOwner().getMaxHealth(), healthBefore + life);
                if (healthBefore < healthAfter) {
                    getOwner().setCurrentHealth(healthAfter);
                    event.getArena().onEvent(new CharacterGainedHealth(event.getArena(), getOwner(), healthAfter - healthBefore));
                }
            }
        }
        super.onEvent(event);
    }
}
