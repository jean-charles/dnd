package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.Organ;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Stimulus;

public interface SensitiveOrgan<S extends Stimulus> extends Organ {
    void stimulate(final S stimuli);
}
