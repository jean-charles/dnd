package com.gayasystem.games.dnd.lifeforms.body.organs.brain;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.Engram;

public interface Brain extends Runnable {
    void handle(Engram engram);
}
