package com.gayasystem.games.dnd.ecosystem.food;

import com.gayasystem.games.dnd.common.Thing;

public abstract class Food extends Thing {
    protected final double nourishment;

    protected Food(double mass, double nourishment) {
        super(mass);
        this.nourishment = nourishment;
    }

    /**
     * Nourishment of the food
     *
     * @return
     */
    public double nourishment() {
        return nourishment;
    }
}
