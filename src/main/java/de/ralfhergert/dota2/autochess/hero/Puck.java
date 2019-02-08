package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Dragon;
import de.ralfhergert.dota2.autochess.species.Elf;

public class Puck extends Character {

    public Puck(String team, int level) {
        super(team, new DoubleEachLevel(level).of(450), 5, 0);
        addAbility(new Elf());
        addAbility(new Dragon());
        // TODO addAbility(new Mage());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(40), new DoubleEachLevel(level).of(50), (long)(1000/1.7), 400));
        addAbility(new ManaRegeneration());
        //addAbility(new IllusoryOrb(level));
    }
}
