package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.classes.DemonHunter;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Demon;

public class Terrorblade extends Character {

    public Terrorblade(String team, int level) {
        super(team, new DoubleEachLevel(level).of(650), 5, 0);
        addAbility(new Demon());
        addAbility(new DemonHunter());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(45), new DoubleEachLevel(level).of(65), (long)(1000/1.4), 205));
        addAbility(new ManaRegeneration());
        // TODO addAbility(?);
    }
}
