package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli;

import com.gayasystem.games.dnd.common.Thing;

public record Sound(Class<? extends Thing> thingClass, SoundSpectrum spectrum, double amplitude) implements Stimulus {
}
