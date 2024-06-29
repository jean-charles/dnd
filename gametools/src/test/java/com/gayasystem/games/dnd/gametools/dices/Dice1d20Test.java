package com.gayasystem.games.dnd.gametools.dices;


import org.junit.jupiter.api.BeforeEach;

public class Dice1d20Test extends AbstractDice {
    @BeforeEach
    public void setUp() {
        dice = Dice1d20.dice;
        max = 20;
    }
}