package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Pressure;

public class Skin extends AbstractSensitiveOrgan<Pressure> {
    public Skin() {
        super(Sense.Touch);
    }

    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Pressure pressure) {
    }
}