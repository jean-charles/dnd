package com.gayasystem.games.dnd.common;

public abstract class Food extends Thing {
    private static final double PERCENT_MASS_LOST = 0.80;
    private final double massDecay;
    private final double nourishmentDecay;
    protected double nourishment;

    /**
     * @param mass
     * @param timeToDecompose in days
     * @param nourishment
     */
    protected Food(double width, double depth, double mass, int timeToDecompose, double nourishment) {
        super(width, depth, mass);
        this.massDecay = (mass * PERCENT_MASS_LOST) / timeToDecompose;
        this.nourishmentDecay = nourishment / timeToDecompose;
        this.nourishment = nourishment;
    }

    @Override
    public void run() {
        if (mass > massDecay)
            mass -= massDecay;
        if (nourishment > 0)
            nourishment -= nourishmentDecay;
    }

    /**
     * Nourishment of the food
     *
     * @return
     */
    public double nourishment() {
        return nourishment;
    }
}
