package com.gayasystem.games.dnd.gametools.dices;

public abstract class Die {
    private final int max;

    protected Die(int max) {
        this.max = max;
    }

    public int roll() {
        int result = (int) (Math.random() * max) + 1;
        return Math.min(result, max);
    }
}
