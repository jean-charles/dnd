package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Smell;

public class Nose extends AbstractSensitiveOrgan<Smell> {
    public Nose() {
        super(Sense.SmellSense);
    }

    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Smell smell) {
    }
}