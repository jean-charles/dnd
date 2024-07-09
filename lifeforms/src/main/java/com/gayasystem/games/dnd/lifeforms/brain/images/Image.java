package com.gayasystem.games.dnd.lifeforms.brain.images;

import com.gayasystem.games.dnd.common.Orientation;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.Engram;

public record Image(Class<? extends Thing> thingClass, Orientation orientation) implements Engram {
    public Image(Class<? extends Thing> thingClass) {
        this(thingClass, null);
    }

    @Override
    public Class<? extends Thing> thingClass() {
        return thingClass;
    }
}