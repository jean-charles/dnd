package com.gayasystem.games.dnd.gametools.dices;

import org.junit.jupiter.api.BeforeEach;

public class Die1D8Test extends AbstractDie {
    @BeforeEach
    public void setUp() {
        die = Die1D8.die;
        max = 8;
    }
}