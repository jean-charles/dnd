package com.gayasystem.games.dnd.common.dices;

import org.junit.Before;

public class Dice1d10Test extends AbstractDice {
    @Before
    public void setUp() {
        dice = Dice1d10.dice;
        max = 10;
    }
}