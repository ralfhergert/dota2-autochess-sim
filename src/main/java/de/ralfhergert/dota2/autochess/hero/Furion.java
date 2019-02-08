package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.classes.Druid;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Elf;

/**
 * The hero Furion.
 */
public class Furion extends Character {

    public Furion(String team, int level) {
        super(team, new DoubleEachLevel(level).of(500), 0, 0);
        addAbility(new Elf());
        addAbility(new Druid());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(45), new DoubleEachLevel(level).of(50), (long)(1000 / 1.4), 400));
        addAbility(new ManaRegeneration());
        // TODO addAbility(?);
    }
}
