package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Image;

public class Eye extends AbstractSensitiveOrgan<Image> {
    public Eye() {
        super(Sense.Sighted);
    }

    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Image image) {
    }
}