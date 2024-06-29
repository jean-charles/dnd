package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeform.brain.sounds.Sound;

import java.util.Collection;

public class EngramComputing {
    public void compute(Collection<Engram> engrams, Moveable moveable) {
        Engram mostImportantEngram = null;
        double mostImportantEngramWeight = 0.0;
        for (Engram engram : engrams) {
            var weight = compute(engram, moveable);
            if (weight > mostImportantEngramWeight) {
                mostImportantEngramWeight = weight;
                mostImportantEngram = engram;
            }
        }
        if (mostImportantEngram instanceof Image)
            moveable.setVelocity(new Velocity());
    }

    private double compute(Engram engram, Moveable moveable) {
        if (engram instanceof Image)
            return 1.0;
        if (engram instanceof Sound)
            return 0.5;
        return 0.0;
    }
}
