package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Image;

public class Eye extends AbstractOrgan<Image> {
    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Image image) {
    }
}