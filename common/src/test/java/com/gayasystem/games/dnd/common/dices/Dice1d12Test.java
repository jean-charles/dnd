package com.gayasystem.games.dnd.common.dices;

import org.junit.jupiter.api.BeforeEach;

public class Dice1d12Test extends AbstractDice {
    @BeforeEach
    public void setUp() {
        dice = Dice1d12.dice;
        max = 12;
    }
}