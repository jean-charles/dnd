package com.gayasystem.games.dnd.common.dices;

public final class Dice1d12 extends Dice {
    public static final Dice1d12 dice = new Dice1d12();

    private Dice1d12() {
        super(12);
    }
}
