package com.gayasystem.games.dnd.lifeform.brain.memories;

import com.gayasystem.games.dnd.common.Thing;

public interface Engram {
    Class<? extends Thing> thingClass();
}
