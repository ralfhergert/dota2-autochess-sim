package de.ralfhergert.dota2.autochess.damage;

import de.ralfhergert.dota2.autochess.character.Character;

/**
 * This class carries the damage dealt by a single auto attack.
 */
public class AutoAttackDamage {

    private final int damage;
    private final Character source;

    public AutoAttackDamage(int damage, Character source) {
        this.damage = damage;
        this.source = source;
    }

    public int getDamage() {
        return damage;
    }

    public Character getSource() {
        return source;
    }
}
