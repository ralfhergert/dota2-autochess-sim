package de.ralfhergert.dota2.autochess.ability;

import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.damage.DamageType;
import de.ralfhergert.dota2.autochess.damage.SpellDamage;
import de.ralfhergert.dota2.autochess.event.CharacterBeingHitEvent;
import de.ralfhergert.dota2.autochess.event.Event;

/**
 * Mana Break is an ability of the {@link de.ralfhergert.dota2.autochess.hero.AntiMage}.
 * Burns an opponents mana on each attack. 50% of the burned mana is dealt as physical
 * damage to the target.
 *
 * This is a passive ability.
 * It does not pierce spell immunity.
 */
public class ManaBreak extends Ability implements DamageAbility {

    private final int manaBurnPerHit;

    public ManaBreak(int level) {
        this.manaBurnPerHit = 30 * level;
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof CharacterBeingHitEvent) {
            CharacterBeingHitEvent beingHitEvent = (CharacterBeingHitEvent)event;
            if (getOwner().equals(beingHitEvent.getAbility().getOwner()) &&
                beingHitEvent.getAbility() instanceof AutoAttackAbility) {
                Character target = beingHitEvent.getCharacter();
                final int manaBefore = target.getCurrentMana();
                final int manaAfter = Math.max(0, manaBefore - manaBurnPerHit);
                target.setCurrentMana(manaAfter);
                if (manaAfter < manaBefore) {
                    target.tryToInflictDamage(event.getArena(), this, new SpellDamage(getOwner(), DamageType.Physical, false, (int)(0.5 * (manaBefore - manaAfter))));
                }
            }
        }
        super.onEvent(event);
    }

    @Override
    public int getPotentialDamageVersus(Character character) {
        return 0;
    }
}
