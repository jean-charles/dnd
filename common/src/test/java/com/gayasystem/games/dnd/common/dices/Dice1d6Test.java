package com.gayasystem.games.dnd.common.dices;

import org.junit.jupiter.api.BeforeEach;

public class Dice1d6Test extends AbstractDice {
    @BeforeEach
    public void setUp() {
        dice = Dice1d6.dice;
        max = 6;
    }
}