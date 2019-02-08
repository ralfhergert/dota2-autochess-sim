package de.ralfhergert.dota2.autochess.log;

import de.ralfhergert.dota2.autochess.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This logger writes events as log messages.
 */
public class EventLogger {

    private static final Logger LOG = LoggerFactory.getLogger(EventLogger.class);

    public EventLogger onEvent(Event event) {
        LOG.info(event.describe());
        return this;
    }
}
