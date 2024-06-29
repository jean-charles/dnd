package com.gayasystem.games.dnd.common.dices;

public final class Dice1d10 extends Dice {
    public static final Dice1d10 dice = new Dice1d10();

    private Dice1d10() {
        super(10);
    }
}
