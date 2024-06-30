package com.gayasystem.games.dnd.lifeform.brain.images;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;

public record Image(Class<? extends Thing> thingClass) implements Engram {
    @Override
    public Class<? extends Thing> thingClass() {
        return thingClass;
    }
}
