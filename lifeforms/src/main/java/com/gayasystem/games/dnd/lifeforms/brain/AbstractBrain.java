package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.PersistedEngram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
public abstract class AbstractBrain implements Brain {
    private final LifeForm lifeForm;
    private final double maxSpeed;
    private final Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private final Collection<SpatialEngram> shortTermMemories = new ArrayList<>();

    @Autowired
    private EngramComputing engramComputing;

    protected AbstractBrain(LifeForm lifeForm, double maxSpeed, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        if (lifeForm == null)
            throw new NullPointerException("lifeForm cannot be null");
        this.lifeForm = lifeForm;
        this.maxSpeed = maxSpeed;
        rememberLongTermMemories(longTermMemories);
    }

    private void rememberLongTermMemories(Map<Class<? extends Thing>, Emotion> longTermMemories) {
        for (var thingClass : longTermMemories.keySet()) {
            var emotion = longTermMemories.get(thingClass);
            Engram engram = new Image(thingClass);
            var persistedEngram = new PersistedEngram(emotion, engram);
            this.longTermMemories.add(persistedEngram);
        }
    }

    @Override
    public void handle(SpatialEngram engram) {
        shortTermMemories.add(engram);
    }

    @Override
    public void run() {
        engramComputing.compute(lifeForm, maxSpeed, longTermMemories, shortTermMemories);
    }

    /* UNIT TESTS ONLY */
    Collection<PersistedEngram> getLongTermMemories() {
        return longTermMemories;
    }

    Collection<SpatialEngram> getShortTermMemories() {
        return shortTermMemories;
    }
}
