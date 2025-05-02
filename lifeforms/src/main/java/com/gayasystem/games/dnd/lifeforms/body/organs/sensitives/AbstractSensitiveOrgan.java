package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.AbstractOrgan;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Stimulus;

public abstract class AbstractSensitiveOrgan<S extends Stimulus> extends AbstractOrgan implements SensitiveOrgan<S> {
    private final Sense sense;

    protected AbstractSensitiveOrgan(final Sense sense) {
        this.sense = sense;
    }

    public void stimulate(final double[] stimuli) {
        brain.handle(new Engram(sense, stimuli));
    }
}
