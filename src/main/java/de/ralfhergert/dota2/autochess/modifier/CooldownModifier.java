package de.ralfhergert.dota2.autochess.modifier;

public interface CooldownModifier {

    long modCooldown(long current);
}
