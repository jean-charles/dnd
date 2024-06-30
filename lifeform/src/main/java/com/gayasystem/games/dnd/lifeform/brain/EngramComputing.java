package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeform.brain.memories.PersistedEngram;

import java.util.Collection;

import static com.gayasystem.games.dnd.lifeform.brain.memories.EmotionConverter.direction;
import static com.gayasystem.games.dnd.lifeform.brain.memories.EmotionConverter.weight;

public class EngramComputing {
    private Collection<PersistedEngram> memories;
    private Moveable moveable;

    public EngramComputing(Collection<PersistedEngram> memories, Moveable moveable) {
        this.memories = memories;
        this.moveable = moveable;
    }

    public void compute(Collection<Engram> engrams) {
        PersistedEngram mostImportantEngram = null;
        double mostImportantEngramWeight = 0.0;

        for (Engram engram : engrams) {
            var foundEngram = compute(engram);
            if (foundEngram != null) {
                var emotion = foundEngram.emotion();
                double weight = weight(emotion);
                if (weight > mostImportantEngramWeight) {
                    mostImportantEngram = foundEngram;
                    mostImportantEngramWeight = weight;
                }
            }
        }

        move(mostImportantEngram);
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

    private void move(PersistedEngram persistedEngram) {
        if (persistedEngram != null) {
            if (persistedEngram.engram() instanceof Image) {
                var emotion = persistedEngram.emotion();
                moveable.setDirection(direction(emotion));
            }
        }
    }
}
