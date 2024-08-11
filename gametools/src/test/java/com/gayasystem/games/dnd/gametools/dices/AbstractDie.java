package com.gayasystem.games.dnd.gametools.dices;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractDie {
    protected static final int NB_ROLLS = 1000000;

    protected Die die;
    protected int max;

    @Test
    public void roll() {
        int rolls = 0;
        for (int i = 0; i < NB_ROLLS; i++) {
            int roll = die.roll();
            rolls += roll;
            assertTrue(roll >= 1);
            assertTrue(roll <= max);
        }
        double triangularNumber = (double) (max * (max + 1)) / 2;
        double average = triangularNumber / max;
        double errorRate = (double) max * 0.5 / 100;
        assertEquals(average, (double) rolls / NB_ROLLS, errorRate);
    }
}
