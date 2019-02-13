package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.event.CharacterBeingHitEvent;
import de.ralfhergert.dota2.autochess.event.Event;
import de.ralfhergert.dota2.autochess.modifier.Stone;

/**
 * When the owner of this ability is hit by a melee attack, this modifier
 * poses a chance to turn the attacker into stone for a given duration.
 */
public class ElementalResistance extends Ability {

    private final double chance;
    private final long durationMs;

    public ElementalResistance(double chance, long durationMs) {
        this.chance = chance;
        this.durationMs = durationMs;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof CharacterBeingHitEvent) {
            CharacterBeingHitEvent characterBeingHitEvent = (CharacterBeingHitEvent)event;
            if (getOwner().equals(characterBeingHitEvent.getCharacter()) &&
                characterBeingHitEvent.getAbility() instanceof AutoAttackAbility &&
                ((AutoAttackAbility)characterBeingHitEvent.getAbility()).getAttackType() == AttackType.Melee &&
                event.getArena().getRandom().nextDouble() < chance) {
                (characterBeingHitEvent.getAbility()).getOwner().addModifier(new Stone(event.getArena().getCurrentTickMs() + durationMs));
            }
        }
    }
}
