package com.gayasystem.games.dnd.ecosystem.food;

import com.gayasystem.games.dnd.common.Thing;

public interface Food extends Thing {
    /**
     * Nourishment of the food
     *
     * @return
     */
    double nourishment();
}
