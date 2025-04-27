package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.Brain;

public interface Organ<S> {
    int nbSignals();

    void stimulate(final S stimuli);

    void connect(final Brain brain);
}
