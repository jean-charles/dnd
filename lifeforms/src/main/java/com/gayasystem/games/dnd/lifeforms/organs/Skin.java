package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.lifeforms.sensitive.stimuli.Pressure;

public class Skin extends AbstractOrgan<Pressure> {
    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Pressure pressure) {
    }
}