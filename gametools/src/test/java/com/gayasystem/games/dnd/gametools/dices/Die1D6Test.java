package com.gayasystem.games.dnd.gametools.dices;


import org.junit.jupiter.api.BeforeEach;

public class Die1D6Test extends AbstractDie {
    @BeforeEach
    public void setUp() {
        die = Die1D6.die;
        max = 6;
    }
}