package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Dragon;
import de.ralfhergert.dota2.autochess.species.Human;

/**
 * The hero Dragon Knight.
 */
public class DragonKnight extends Character {

    public DragonKnight(String team, int level) {
        super(team, new DoubleEachLevel(level).of(800), 10, 0);
        addAbility(new Human());
        addAbility(new Dragon());
        // TODO addAbility(new Knight());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(50), new DoubleEachLevel(level).of(60), (long)(1000 / 1.2), 205));
        addAbility(new ManaRegeneration());
        //addAbility(new ElderDragonForm(level));
    }
}
