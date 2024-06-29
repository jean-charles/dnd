package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeform.brain.memories.PersistedEngram;

import java.util.ArrayList;
import java.util.Collection;

public class Brain implements Runnable {
    private Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private Collection<Engram> shortTermMemories = new ArrayList<>();

    public void handle(Engram engram) {
        shortTermMemories.add(engram);
    }

    @Override
    public void run() {
    }
}
