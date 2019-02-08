package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Troll;

public class BatRider extends Character {

    public BatRider(String team, int level) {
        super(team, new DoubleEachLevel(level).of(500), 5, 0);
        addAbility(new Troll());
        // TODO addAbility(new Knight());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(40), new DoubleEachLevel(level).of(55), (long)(1000/1.3), 400));
        addAbility(new ManaRegeneration());
        // TODO addAbility(new StickyNapalm());
    }
}
