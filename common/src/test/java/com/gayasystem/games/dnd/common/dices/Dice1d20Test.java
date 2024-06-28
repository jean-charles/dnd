package com.gayasystem.games.dnd.common.dices;

import org.junit.Before;

public class Dice1d20Test extends AbstractDice {
    @Before
    public void setUp() {
        dice = Dice1d20.dice;
        max = 20;
    }
}