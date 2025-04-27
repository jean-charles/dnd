package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.lifeforms.sensitive.stimuli.Smell;

public class Nose extends AbstractOrgan<Smell> {
    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Smell smell) {
    }
}