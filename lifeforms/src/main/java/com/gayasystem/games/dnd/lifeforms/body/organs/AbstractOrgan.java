package com.gayasystem.games.dnd.lifeforms.body.organs;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.Brain;

public abstract class AbstractOrgan implements Organ {
    protected Brain brain;

    @Override
    public void connect(final Brain brain) {
        this.brain = brain;
    }
}
