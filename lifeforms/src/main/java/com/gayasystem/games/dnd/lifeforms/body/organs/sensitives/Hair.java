package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Pressure;

public class Hair extends AbstractOrgan<Pressure> {
    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Pressure pressure) {
    }
}