package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.Brain;

public abstract class AbstractOrgan<S> implements Organ<S> {
    private Brain brain;

    @Override
    public void connect(final Brain brain) {
        this.brain = brain;
    }
}
