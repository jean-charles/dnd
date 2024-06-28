package com.gayasystem.games.dnd.common.dices;

import org.junit.Before;

public class Dice1d8Test extends AbstractDice {
    @Before
    public void setUp() {
        dice = Dice1d8.dice;
        max = 8;
    }
}