package de.ralfhergert.dota2.autochess.modifier;

/**
 * Marker interface for modifiers effecting the auto attack speed.
 * Attack speed is measured as "duration until the next strike in ms"
 */
public interface AutoAttackSpeedModifier extends Modifier<Long> {}
