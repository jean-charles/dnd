package com.gayasystem.games.dnd.common.dices;

import org.junit.Before;

public class Dice1d12Test extends AbstractDice {
    @Before
    public void setUp() {
        dice = Dice1d12.dice;
        max = 12;
    }
}