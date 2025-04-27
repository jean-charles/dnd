package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.lifeforms.sensitive.stimuli.Sound;

public class Ear extends AbstractOrgan<Sound> {
    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Sound sound) {
    }
}