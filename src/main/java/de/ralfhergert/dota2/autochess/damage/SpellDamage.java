package de.ralfhergert.dota2.autochess.damage;

import de.ralfhergert.dota2.autochess.character.Character;

public class SpellDamage {

    private final Character source;
    private final DamageType type;
    private final boolean piercesSpellImmunity;
    private final int damage;

    public SpellDamage(Character source, DamageType type, boolean piercesSpellImmunity, int damage) {
        this.source = source;
        this.type = type;
        this.piercesSpellImmunity = piercesSpellImmunity;
        this.damage = damage;
    }

    public Character getSource() {
        return source;
    }

    public DamageType getType() {
        return type;
    }

    public boolean isPiercesSpellImmunity() {
        return piercesSpellImmunity;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "SpellDamage{damage=" + damage +
            ", type=" + type +
            ", piercesSpellImmunity=" + piercesSpellImmunity +
            ", source=" + source +
            "}";
    }
}
