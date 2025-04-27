package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.lifeforms.brain.Brain;

public abstract class AbstractOrgan<S> implements Organ<S> {
    private Brain brain;

    @Override
    public void connect(final Brain brain) {
        this.brain = brain;
    }
}
