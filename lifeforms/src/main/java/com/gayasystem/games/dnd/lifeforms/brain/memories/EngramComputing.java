package com.gayasystem.games.dnd.lifeforms.brain.memories;

import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.neutral;

@Service
public class EngramComputing {
    private PersistedEngram search(Collection<PersistedEngram> memories, SpatialEngram engram) {
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

    private void move(Moveable moveable, double maxSpeed, Collection<SpatialEmotionalEngram> spatialEmotionalEngrams) {
        var mostImportantEngram = findMostImportantEngram(spatialEmotionalEngrams);
        var speedRate = computeSpeed(mostImportantEngram.emotion());
        var orientation = computeOrientation(mostImportantEngram);
        var engram = mostImportantEngram.engram();
        if (engram != null) {
            var destination = new CircularCoordinate(engram.origin().rho(), orientation);
            moveable.velocity(maxSpeed * speedRate, destination);
        }
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
                return 1.0;
            }
            case attracted -> {
                return 0.75;
            }
            case neutral -> {
                return 0.5;
            }
        }
        return 0;
    }

    private Orientation computeOrientation(SpatialEmotionalEngram engram) {
        switch (engram.emotion()) {
            case scared -> {
                return engram.engram().origin().orientation().opposite();
            }
            case attracted -> {
                return engram.engram().origin().orientation();
            }
        }
        return new Orientation(0);
    }

    public void compute(Moveable moveable, double maxSpeed, Emotion defaultEmotion, Collection<PersistedEngram> memories, Collection<SpatialEngram> engrams) {
        Collection<SpatialEmotionalEngram> emotionalEngrams = new ArrayList<>();

        for (var engram : engrams) {
            var foundMemory = search(memories, engram);
            if (foundMemory != null) {
                var emotion = foundMemory.emotion();
                emotionalEngrams.add(new SpatialEmotionalEngram(engram, emotion));
            } else {
                emotionalEngrams.add(new SpatialEmotionalEngram(engram, defaultEmotion));
            }
        }

        move(moveable, maxSpeed, emotionalEngrams);
    }
}
