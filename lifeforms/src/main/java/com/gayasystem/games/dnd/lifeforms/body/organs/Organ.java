package com.gayasystem.games.dnd.lifeforms.body.organs;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.Brain;

public interface Organ {
    int nbSignals();

    void connect(final Brain brain);
}
