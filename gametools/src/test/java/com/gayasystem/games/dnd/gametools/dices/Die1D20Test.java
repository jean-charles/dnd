package com.gayasystem.games.dnd.gametools.dices;


import org.junit.jupiter.api.BeforeEach;

public class Die1D20Test extends AbstractDie {
    @BeforeEach
    public void setUp() {
        die = Die1D20.die;
        max = 20;
    }
}