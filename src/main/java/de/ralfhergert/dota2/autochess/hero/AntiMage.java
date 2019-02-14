package de.ralfhergert.dota2.autochess.hero;

import de.ralfhergert.dota2.autochess.ability.AutoAttackAbility;
import de.ralfhergert.dota2.autochess.ability.ManaBreak;
import de.ralfhergert.dota2.autochess.ability.ManaRegeneration;
import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.classes.DemonHunter;
import de.ralfhergert.dota2.autochess.progression.DoubleEachLevel;
import de.ralfhergert.dota2.autochess.species.Elf;

/**
 * The hero Anti Mage.
 */
public class AntiMage extends Character {

    public AntiMage(String team, int level) {
        super(team, new DoubleEachLevel(level).of(500), 0, 0.1);
        addAbility(new Elf());
        addAbility(new DemonHunter());
        addAbility(new AutoAttackAbility(new DoubleEachLevel(level).of(45), new DoubleEachLevel(level).of(55), (long)(1000 / 1.2), 205));
        addAbility(new ManaRegeneration());
        addAbility(new ManaBreak(level));
    }
}
