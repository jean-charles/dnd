package com.gayasystem.games.dnd.lifeforms.brain.sounds;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.Engram;

public record Sound(Class<? extends Thing> thingClass, SoundSpectrum spectrum, double amplitude) implements Engram {
    @Override
    public Class<? extends Thing> thingClass() {
        return thingClass;
    }
}
