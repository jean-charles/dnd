package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli;

import com.gayasystem.games.dnd.common.Thing;

public record Image(Class<? extends Thing> thingClass) implements Stimulus {
}
