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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.attracted;
import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.scared;

@Component
public abstract class AbstractBrain implements Brain {
    private final Moveable moveable;
    private final double maxSpeed;
    private final Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private final Collection<SpatialEngram> shortTermMemories = new ArrayList<>();

    @Autowired
    private EngramComputing engramComputing;

    protected AbstractBrain(LifeForm lifeForm, double maxSpeed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        if (lifeForm == null)
            throw new NullPointerException("lifeForm cannot be null");
        this.moveable = lifeForm;
        this.maxSpeed = maxSpeed;
        rememberAttractedByMemories(attractedBy);
        rememberScaredByMemories(scaredBy);
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

    @Override
    public void handle(SpatialEngram engram) {
        shortTermMemories.add(engram);
    }

    @Override
    public void run() {
        engramComputing.compute(moveable, maxSpeed, longTermMemories, shortTermMemories);
    }

    /* UNIT TESTS ONLY */
    Collection<PersistedEngram> getLongTermMemories() {
        return longTermMemories;
    }

    Collection<SpatialEngram> getShortTermMemories() {
        return shortTermMemories;
    }
}
