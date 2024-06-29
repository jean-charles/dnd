package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeform.brain.memories.PersistedEngram;

import java.util.ArrayList;
import java.util.Collection;

public class Brain implements Runnable {
    private final EngramComputing engramComputing = new EngramComputing();

    private Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private Collection<Engram> shortTermMemories = new ArrayList<>();

    private final Moveable moveable;

    public Brain(Moveable moveable) {
        this.moveable = moveable;
    }

    public void handle(Engram engram) {
        shortTermMemories.add(engram);
    }

    @Override
    public void run() {
        engramComputing.compute(shortTermMemories, moveable);
    }
}
