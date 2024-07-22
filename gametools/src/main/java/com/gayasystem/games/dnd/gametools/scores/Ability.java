package com.gayasystem.games.dnd.gametools.scores;

public record Ability(int score, int modifier) {
    public Ability(int score) {
        this(score, (score - 10) / 2);
    }

    public Ability(int score, int modifier) {
        if (score < 1 || score > 20) throw new IndexOutOfBoundsException();
        this.score = score;

        int mod = (score - 10) / 2;
        if (mod != modifier) throw new UnsupportedOperationException();
        this.modifier = modifier;
    }
}
