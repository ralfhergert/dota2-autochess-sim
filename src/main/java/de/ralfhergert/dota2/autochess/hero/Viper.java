package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.classes.Assassin;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Dragon;

/**
 * The hero Viper.
 */
public class Viper extends Character {

    public Viper(String team, int level) {
        super(team, new DoubleEachLevel(level).of(550), 5, 0.2);
        addAbility(new Dragon());
        addAbility(new Assassin());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(50), new DoubleEachLevel(level).of(60), (long)(1000 / 1.0), 400));
        addAbility(new ManaRegeneration());
        // TODO addAbility(new ViperStrike(level));
    }
}
