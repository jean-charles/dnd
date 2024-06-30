package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeform.brain.memories.PersistedEngram;
import com.gayasystem.games.dnd.lifeform.brain.memories.SpatialEngram;

import java.util.ArrayList;
import java.util.Collection;

import static com.gayasystem.games.dnd.lifeform.brain.memories.Emotion.attracted;
import static com.gayasystem.games.dnd.lifeform.brain.memories.Emotion.scared;

public class Brain implements Runnable {
    private final EngramComputing engramComputing;

    private final Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private final Collection<SpatialEngram> shortTermMemories = new ArrayList<>();

    public Brain(Moveable moveable, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
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
        engramComputing.compute(shortTermMemories);
    }
}
