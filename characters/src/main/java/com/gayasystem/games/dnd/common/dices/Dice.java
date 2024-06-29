package com.gayasystem.games.dnd.common.dices;

public abstract class Dice {
    private final int max;

    protected Dice(int max) {
        this.max = max;
    }

    public int roll() {
        int result = (int) (Math.random() * max) + 1;
        return Math.min(result, max);
    }
}
