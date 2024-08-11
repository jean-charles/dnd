package com.gayasystem.games.dnd.gametools.dices;

import org.junit.jupiter.api.BeforeEach;

public class Die1D12Test extends AbstractDie {
    @BeforeEach
    public void setUp() {
        die = Die1D12.dice;
        max = 12;
    }
}