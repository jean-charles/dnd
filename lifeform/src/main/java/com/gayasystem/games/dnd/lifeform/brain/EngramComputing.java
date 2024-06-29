package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeform.brain.memories.PersistedEngram;
import com.gayasystem.games.dnd.lifeform.brain.sounds.Sound;

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
        if (mostImportantEngram != null) {
            if (mostImportantEngram.engram() instanceof Image) {
                var emotion = mostImportantEngram.emotion();
                moveable.setDirection(direction(emotion));
            }
        }
    }

    private PersistedEngram compute(Engram engram) {
        if (engram instanceof Image)
            return computeImage((Image) engram);
        if (engram instanceof Sound)
            return computeSound((Sound) engram);
        return null;
    }

    private PersistedEngram computeImage(Image image) {
        for (var memory : memories) {
            if (memory.engram().equals(image))
                return memory;
        }
        return null;
    }

    private PersistedEngram computeSound(Sound sound) {
        for (var memory : memories) {
            if (memory.engram().equals(sound))
                return memory;
        }
        return null;
    }
}
