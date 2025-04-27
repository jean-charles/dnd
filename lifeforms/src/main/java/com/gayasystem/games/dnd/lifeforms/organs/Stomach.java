package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.common.Food;

public class Stomach extends AbstractOrgan<Food> {
    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Food stimuli) {

    }
}
