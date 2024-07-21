package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;

public interface Brain extends Runnable {
    void handle(SpatialEngram engram);
}
