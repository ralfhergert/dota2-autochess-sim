package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.event.CharacterBeingDamagedEvent;
import de.ralfhergert.dota2.autochess.event.CharacterGainedMana;
import de.ralfhergert.dota2.autochess.event.Event;

/**
 * Character regenerates Mana whenever it deals damage or is damage dealt to.
 * I guess for every 10 points of damage inflicted 1 point of Mana is earned.
 */
public class ManaRegeneration extends Ability {

    @Override
    public void onEvent(Event event) {
        if (event instanceof CharacterBeingDamagedEvent) {
            CharacterBeingDamagedEvent beingDamagedEvent = (CharacterBeingDamagedEvent)event;
            if (getOwner().equals(beingDamagedEvent.getAttacker()) ||
                getOwner().equals(beingDamagedEvent.getCharacter())) {
                final int manaGain = beingDamagedEvent.getDamage() / 10;
                final int manaBefore = getOwner().getCurrentMana();
                final int manaAfter = Math.max(getOwner().getMaxMana(), manaBefore + manaGain);
                getOwner().setCurrentMana(manaAfter);
                if (manaBefore < manaAfter) {
                    event.getArena().onEvent(new CharacterGainedMana(event.getArena(), getOwner(), manaAfter - manaBefore));
                }
            }
        }
        super.onEvent(event);
    }
}
