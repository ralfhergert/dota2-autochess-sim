package de.ralfhergert.dota2.autochess.classes;

import de.ralfhergert.dota2.autochess.ability.Ability;
import de.ralfhergert.dota2.autochess.species.Demon;

/**
 * Demon Hunters influence {@link Demon}s {@link de.ralfhergert.dota2.autochess.modifier.FelPower}.
 *  - 1 Demon Hunter negates the enemy Fel Power
 *  - 2 Demon Hunter lets all allied demons keep their Fel Power.
 *
 *  The implementation of this mechanic is in the {@link Demon} ability.
 */
public class DemonHunter extends Ability {}
