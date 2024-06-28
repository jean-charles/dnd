package com.gayasystem.games.dnd.common.dices;

import org.junit.Before;

public class Dice1d6Test extends AbstractDice {
    @Before
    public void setUp() {
        dice = Dice1d6.dice;
        max = 6;
    }
}