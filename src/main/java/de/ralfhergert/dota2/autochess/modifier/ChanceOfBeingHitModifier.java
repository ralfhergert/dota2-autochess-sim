package de.ralfhergert.dota2.autochess.modifier;

/**
 * This is a marker-interface for all modifiers of the "chance of being hit".
 * Chance of being hit is given as double thus 0 equals 0% and 1 equals 100%.
 */
public interface ChanceOfBeingHitModifier extends Modifier<Double> {}
