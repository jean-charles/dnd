package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Flavour;

public class Tong extends AbstractSensitiveOrgan<Flavour> {
    public Tong() {
        super(Sense.Taste);
    }

    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Flavour flavour) {
    }
}