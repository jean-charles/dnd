package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.PersistedEngram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;

import java.util.ArrayList;
import java.util.Collection;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.attracted;
import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.scared;

public class Brain implements Runnable {
    private final EngramComputing engramComputing;
    private final Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private final Collection<SpatialEngram> shortTermMemories = new ArrayList<>();
    private final double speed;

    private double currentSpeed;

    public Brain(Moveable moveable, double speed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        this.speed = speed;
        rememberAttractedByMemories(attractedBy);
        rememberScaredByMemories(scaredBy);
        engramComputing = new EngramComputing(longTermMemories, moveable);
    }

    private void rememberAttractedByMemories(Collection<Class<? extends Thing>> attractedBy) {
        for (var thingClass : attractedBy) {
            Engram engram = new Image(thingClass);
            var persistedEngram = new PersistedEngram(attracted, engram);
            longTermMemories.add(persistedEngram);
        }
    }

    private void rememberScaredByMemories(Collection<Class<? extends Thing>> scaredBy) {
        for (var thingClass : scaredBy) {
            Engram engram = new Image(thingClass);
            var persistedEngram = new PersistedEngram(scared, engram);
            longTermMemories.add(persistedEngram);
        }
    }

    public void handle(SpatialEngram engram) {
        shortTermMemories.add(engram);
    }

    @Override
    public void run() {
        currentSpeed = speed;
        engramComputing.compute(shortTermMemories);
    }

    public double speed() {
        return currentSpeed;
    }
}
