package de.ralfhergert.dota2.autochess;

import de.ralfhergert.dota2.autochess.hero.AntiMage;
import de.ralfhergert.dota2.autochess.log.EventLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This one-on-one-runner lets two characters fight against each other.
 */
public class OneOnOne {

    private static final Logger LOG = LoggerFactory.getLogger(OneOnOne.class);

    private OneOnOne() { /* no need to instantiate */ }

    public static void main(String... args) {
        Arena arena = new Arena()
            .addEventLogger(new EventLogger())
            .addCharacter(new AntiMage("A", 1))
            .addCharacter(new AntiMage("B", 1))
            .initialize();

        while (!arena.isFightOver()) {
            arena.step();
        }

        LOG.info("simulated fight is over after {} ms", arena.getCurrentTickMs());
        LOG.info("winning team: {}", arena.getWinningTeam());
        LOG.info("losing team: {}", arena.getLosingTeam());
    }
}
