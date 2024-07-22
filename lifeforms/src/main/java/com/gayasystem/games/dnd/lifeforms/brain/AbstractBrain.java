package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.PersistedEngram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.attracted;
import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.scared;

public abstract class AbstractBrain implements Brain {
    private final Moveable moveable;
    private final double speed;
    private final Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private final Collection<SpatialEngram> shortTermMemories = new ArrayList<>();

    private double currentSpeed;

    @Autowired
    private EngramComputing engramComputing;

    protected AbstractBrain(LifeForm lifeForm, double speed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        this.moveable = lifeForm;
        this.speed = speed;
        rememberAttractedByMemories(attractedBy);
        rememberScaredByMemories(scaredBy);
    }

    private void rememberAttractedByMemories(Collection<Class<? extends Thing>> attractedBy) {
        for (var thingClass : attractedBy) {
            Engram engram = new Image(thingClass, null);
            var persistedEngram = new PersistedEngram(attracted, engram);
            longTermMemories.add(persistedEngram);
        }
    }

    private void rememberScaredByMemories(Collection<Class<? extends Thing>> scaredBy) {
        for (var thingClass : scaredBy) {
            Engram engram = new Image(thingClass, null);
            var persistedEngram = new PersistedEngram(scared, engram);
            longTermMemories.add(persistedEngram);
        }
    }

    @Override
    public void handle(SpatialEngram engram) {
        shortTermMemories.add(engram);
    }

    @Override
    public void run() {
        currentSpeed = speed;
        engramComputing.compute(moveable, longTermMemories, shortTermMemories);
    }

    public double speed() {
        return currentSpeed;
    }
}
