package com.gayasystem.games.dnd.gametools.scores;

import com.gayasystem.games.dnd.gametools.dices.Die1D6;

public record Ability(int score, int modifier) {
    public Ability() {
        this(setUp());
    }

    public Ability(int score, int modifier) {
        if (score < 1 || score > 30) throw new IndexOutOfBoundsException();
        this.score = score;

        int mod = (score - 10) / 2;
        if (mod != modifier) throw new UnsupportedOperationException();
        this.modifier = modifier;
    }

    public Ability(int score) {
        this(score, (score - 10) / 2);
    }

    private static int setUp() {
        var die = Die1D6.die;
        var score = 0;
        for (int roll = 1; roll <= 3; roll++)
            score += die.roll();
        return score;
    }

    public Ability add(Ability ability) {
        if (ability == null)
            return this;
        var score = this.score;
        score += ability.score;
        return new Ability(score);
    }
}
