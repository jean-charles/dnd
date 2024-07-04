package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.common.Direction;
import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.memories.*;

import java.util.Collection;
import java.util.List;

import static com.gayasystem.games.dnd.lifeform.brain.memories.EmotionConverter.direction;
import static com.gayasystem.games.dnd.lifeform.brain.memories.EmotionConverter.weight;

public class EngramComputing {
    private final Collection<PersistedEngram> memories;
    private final Moveable moveable;

    public EngramComputing(Collection<PersistedEngram> memories, Moveable moveable) {
        this.memories = memories;
        this.moveable = moveable;
    }

    public void compute(Collection<SpatialEngram> engrams) {
        Collection<SpatialEmotionalEngram> mostImportantEngrams = List.of();
        double mostImportantEngramWeight = 0.0;

        for (SpatialEngram engram : engrams) {
            var foundMemory = compute(engram.engram());
            if (foundMemory != null) {
                var emotion = foundMemory.emotion();
                double weight = weight(emotion);
                if (weight >= mostImportantEngramWeight) {
                    mostImportantEngrams.add(convert(engram, emotion));
                    mostImportantEngramWeight = weight;
                }
            }
        }

        move(mostImportantEngrams);
    }

    private SpatialEmotionalEngram convert(SpatialEngram engram, Emotion emotion) {
        return new SpatialEmotionalEngram(engram.engram(), engram.origin(), emotion);
    }

    private PersistedEngram compute(Engram engram) {
        Class<? extends Thing> thing = engram.thingClass();
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

    private void move(Collection<SpatialEmotionalEngram> engrams) {
        Collection<Direction> directions = List.of();
        for (SpatialEmotionalEngram engram : engrams) {
            directions.add(direction(engram.origin(), engram.emotion()));
        }
        moveable.setDirection(null);
    }
}
