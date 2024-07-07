package com.gayasystem.games.dnd.lifeform.brain.memories;

import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.memories.emotions.Emotion;

import java.util.ArrayList;
import java.util.Collection;

import static com.gayasystem.games.dnd.lifeform.brain.memories.emotions.Emotion.neutral;
import static com.gayasystem.games.dnd.lifeform.brain.memories.emotions.EmotionConverter.direction;

public class EngramComputing {
    private final Collection<PersistedEngram> memories;
    private final Moveable moveable;

    public EngramComputing(Collection<PersistedEngram> memories, Moveable moveable) {
        this.memories = memories;
        this.moveable = moveable;
    }

    public void compute(Collection<SpatialEngram> engrams) {
        Collection<SpatialEmotionalEngram> emotionalEngrams = new ArrayList<>();

        for (var engram : engrams) {
            var foundMemory = compute(engram);
            if (foundMemory != null) {
                var emotion = foundMemory.emotion();
                emotionalEngrams.add(new SpatialEmotionalEngram(engram, emotion));
            } else {
                Emotion emotion = neutral;
                emotionalEngrams.add(new SpatialEmotionalEngram(engram, emotion));
            }
        }

        move(emotionalEngrams);
    }

    private PersistedEngram compute(SpatialEngram engram) {
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
        Collection<SphericalCoordinate> destinations = new ArrayList<>();
        for (var spatialEmotionalEngram : spatialEmotionalEngrams) {
            destinations.add(direction(spatialEmotionalEngram.engram().origin(), spatialEmotionalEngram.emotion()));
        }
        var speed = computeSpeed();
        var destination = computeDestination(destinations);
        moveable.setVelocity(destination, speed);
    }

    private double computeSpeed() {
        return 0;
    }

    private SphericalCoordinate computeDestination(Collection<SphericalCoordinate> destinations) {
        return null;
    }
}
