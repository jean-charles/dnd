package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Sound;

public class Ear extends AbstractOrgan<Sound> {
    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Sound sound) {
    }
}