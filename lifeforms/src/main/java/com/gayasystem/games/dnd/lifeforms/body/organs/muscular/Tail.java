package com.gayasystem.games.dnd.lifeforms.body.organs.muscular;

public class Tail extends AbstractMuscularOrgan {
    private double[] signals;

    @Override
    public int nbSignals() {
        return 1;
    }

    @Override
    public void handleSignals(final double[] signals) {
        if (signals == null)
            throw new RuntimeException("Invalid signals: null");
        if (signals.length != nbSignals())
            throw new RuntimeException("Invalid signals size: %d. Expected size %d".formatted(signals.length, nbSignals()));
        this.signals = signals;
    }

    public double speed() {
        return signals[0];
    }
}
