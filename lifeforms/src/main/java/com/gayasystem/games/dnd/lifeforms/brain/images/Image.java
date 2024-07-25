package com.gayasystem.games.dnd.lifeforms.brain.images;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.Engram;

public record Image(Class<? extends Thing> thingClass) implements Engram {
    @Override
    public Class<? extends Thing> thingClass() {
        return thingClass;
    }
}
