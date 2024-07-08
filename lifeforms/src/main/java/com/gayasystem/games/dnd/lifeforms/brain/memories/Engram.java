package com.gayasystem.games.dnd.lifeforms.brain.memories;

import com.gayasystem.games.dnd.common.Thing;

public interface Engram {
    Class<? extends Thing> thingClass();
}
