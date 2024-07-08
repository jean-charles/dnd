package com.gayasystem.games.dnd.lifeforms.brain.memories;

import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;

import java.util.ArrayList;
import java.util.Collection;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.neutral;

public class EngramComputing {
    private final Collection<PersistedEngram> memories;
    private final Moveable moveable;

    public EngramComputing(Collection<PersistedEngram> memories, Moveable moveable) {
        this.memories = memories;
        this.moveable = moveable;
    }

    private PersistedEngram search(SpatialEngram engram) {
        Class<? extends Thing> thing = engram.engram().thingClass();
        PersistedEngram similarMemory = null;

        for (var memory : memories) {
            var memoryThing = memory.engram().thingClass();
            if (memoryThing.isAssignableFrom(thing)) {
                if (memoryThing.equals(thing))
                    return memory;
                similarMemory = memory;
            }
        }
        return similarMemory;
    }

    private void move(Collection<SpatialEmotionalEngram> spatialEmotionalEngrams) {
        var engram = findMostImportantEngram(spatialEmotionalEngrams);
        var speed = computeSpeed(engram.emotion());
        var destination = computeDestination(engram);
        moveable.velocity(speed, destination);
    }

    private SpatialEmotionalEngram findMostImportantEngram(Collection<SpatialEmotionalEngram> spatialEmotionalEngrams) {
        var mostImportantEngram = new SpatialEmotionalEngram(null, neutral);
        double closedDistance = Double.MAX_VALUE;
        for (var spatialEmotionalEngram : spatialEmotionalEngrams) {
            var distance = spatialEmotionalEngram.engram().origin().rho().doubleValue();
            if (distance < closedDistance) {
                closedDistance = distance;
                mostImportantEngram = spatialEmotionalEngram;
            }
        }
        return mostImportantEngram;
    }

    private double computeSpeed(Emotion emotion) {
        switch (emotion) {
            case scared -> {
                return 20;
            }
            case attracted -> {
                return 10;
            }
            case neutral -> {
                return 0;
            }
        }
        return 0;
    }

    private SphericalCoordinate computeDestination(SpatialEmotionalEngram engram) {
        switch (engram.emotion()) {
            case scared -> {
                return engram.engram().origin().opposite();
            }
            case attracted -> {
                return engram.engram().origin();
            }
        }
        return new SphericalCoordinate(0, 0, 0);
    }

    public void compute(Collection<SpatialEngram> engrams) {
        Collection<SpatialEmotionalEngram> emotionalEngrams = new ArrayList<>();

        for (var engram : engrams) {
            var foundMemory = search(engram);
            if (foundMemory != null) {
                var emotion = foundMemory.emotion();
                emotionalEngrams.add(new SpatialEmotionalEngram(engram, emotion));
            } else {
                emotionalEngrams.add(new SpatialEmotionalEngram(engram, neutral));
            }
        }

        move(emotionalEngrams);
    }
}
