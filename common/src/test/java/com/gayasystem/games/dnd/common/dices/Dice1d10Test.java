package com.gayasystem.games.dnd.common.dices;


import org.junit.jupiter.api.BeforeEach;

public class Dice1d10Test extends AbstractDice {
    @BeforeEach
    public void setUp() {
        dice = Dice1d10.dice;
        max = 10;
    }
}