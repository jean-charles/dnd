package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.lifeforms.brain.Brain;

public interface Organ<S> {
    int nbSignals();

    void stimulate(final S stimuli);

    void connect(final Brain brain);
}
