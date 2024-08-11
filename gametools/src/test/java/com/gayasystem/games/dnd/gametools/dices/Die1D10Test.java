package com.gayasystem.games.dnd.gametools.dices;


import org.junit.jupiter.api.BeforeEach;

public class Die1D10Test extends AbstractDie {
    @BeforeEach
    public void setUp() {
        die = Die1D10.dice;
        max = 10;
    }
}