package com.gayasystem.games.dnd.common.dices;

public final class Dice1d8 extends Dice {
    public static final Dice1d8 dice = new Dice1d8();

    private Dice1d8() {
        super(8);
    }
}
