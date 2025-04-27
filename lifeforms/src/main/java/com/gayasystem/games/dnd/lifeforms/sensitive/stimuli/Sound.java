package com.gayasystem.games.dnd.lifeforms.sensitive.stimuli;

import com.gayasystem.games.dnd.common.Thing;

public record Sound(Class<? extends Thing> thingClass, SoundSpectrum spectrum, double amplitude) {
}
