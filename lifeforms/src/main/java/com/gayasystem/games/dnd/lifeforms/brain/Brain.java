package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.lifeforms.brain.memories.Engram;

public interface Brain extends Runnable {
    void handle(Engram engram);
}
