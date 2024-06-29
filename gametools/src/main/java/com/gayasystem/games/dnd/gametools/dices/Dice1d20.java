package com.gayasystem.games.dnd.gametools.dices;

public final class Dice1d20 extends Dice {
    public static final Dice1d20 dice = new Dice1d20();

    private Dice1d20() {
        super(20);
    }
}
