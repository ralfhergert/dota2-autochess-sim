package de.ralfhergert.dota2.autochess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.ralfhergert.dota2.autochess.character.Character;
import de.ralfhergert.dota2.autochess.event.Event;
import de.ralfhergert.dota2.autochess.log.EventLogger;

public class Arena {

    private final Random random = new Random();

    private long currentTickMs = 0;
    private List<Long> nextRegisteredTicks = new ArrayList<>();

    private List<Character> characters = new ArrayList<>();
    private List<EventLogger> eventLoggers = new ArrayList<>();

    public Arena addCharacter(Character character) {
        characters.add(character);
        return this;
    }

    public Arena addEventLogger(EventLogger logger) {
        eventLoggers.add(logger);
        return this;
    }

    public Arena initialize() {
        characters.forEach(character -> character.initialize(this));
        return this;
    }

    public Arena registerTick(long tick) {
        nextRegisteredTicks.add(tick);
        return this;
    }

    public long getCurrentTickMs() {
        return currentTickMs;
    }

    public Arena step() {
        if (!nextRegisteredTicks.isEmpty()) {
            nextRegisteredTicks.sort(Long::compareTo);
            currentTickMs = Math.max(currentTickMs, nextRegisteredTicks.get(0));
            nextRegisteredTicks = nextRegisteredTicks.stream()
                .filter(tick -> tick > currentTickMs)
                .collect(Collectors.toList());
        }
        characters.forEach(character -> character.performOn(this));
        return this;
    }

    public boolean isFightOver() {
        return characters.stream()
            .filter(Character::isAlive)
            .map(Character::getTeam)
            .collect(Collectors.toSet())
            .size() < 2;
    }

    public Stream<Character> getEnemiesOf(String team) {
        return characters.stream().filter(character -> !character.getTeam().equals(team));
    }

    public Stream<Character> getAllOfTeam(String team) {
        return characters.stream().filter(character -> character.getTeam().equals(team));
    }

    public String getWinningTeam() {
        return characters.stream()
            .filter(Character::isAlive)
            .map(Character::getTeam)
            .findFirst()
            .orElse(null);
    }

    public String getLosingTeam() {
        return characters.stream()
            .filter(character -> !character.isAlive())
            .map(Character::getTeam)
            .findFirst()
            .orElse(null);
    }

    public Arena onEvent(Event event) {
        eventLoggers.forEach(eventLogger -> eventLogger.onEvent(event));
        characters.forEach(character -> character.onEvent(event));
        return this;
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public String toString() {
        return "Arena{" +
            "currentTickMs=" + currentTickMs +
            ", characters=" + characters +
            '}';
    }
}
