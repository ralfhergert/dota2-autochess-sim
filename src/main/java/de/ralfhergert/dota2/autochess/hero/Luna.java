package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.classes.Knight;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Elf;

/**
 * The hero Luna.
 */
public class Luna extends Character {

    public Luna(String team, int level) {
        super(team, new DoubleEachLevel(level).of(500), 5, 0);
        addAbility(new Elf());
        addAbility(new Knight());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(50), new DoubleEachLevel(level).of(60), (long)(1000 / 1.3), 400));
        addAbility(new ManaRegeneration());
        // TODO addAbility(new MoonGlaives(level));
    }
}
