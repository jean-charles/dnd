package com.gayasystem.games.dnd.gametools.dices;

import org.junit.jupiter.api.BeforeEach;

public class Dice1d8Test extends AbstractDice {
    @BeforeEach
    public void setUp() {
        dice = Dice1d8.dice;
        max = 8;
    }
}