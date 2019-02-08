package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Demon;

/**
 * The hero Chaos Knight.
 */
public class ChaosKnight extends Character {

    public ChaosKnight(String team, int level) {
        super(team, new DoubleEachLevel(level).of(750), 5, 0);
        addAbility(new Demon());
        // TODO addAbility(new Knight());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(40), new DoubleEachLevel(level).of(110), (long)(1000/1.3), 205));
        addAbility(new ManaRegeneration());
        // TODO addAbility(new ChaosBolt());
    }
}
