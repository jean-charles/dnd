package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.lifeforms.sensitive.stimuli.Image;

public class Eye extends AbstractOrgan<Image> {
    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Image image) {
    }
}