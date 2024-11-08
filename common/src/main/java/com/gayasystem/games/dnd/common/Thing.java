package com.gayasystem.games.dnd.common;

public abstract class Thing implements Runnable {
    protected double width;
    protected double depth;
    protected double mass;

    /**
     * @param width Distance between shoulders in meter.
     * @param depth Distance front to back in meter.
     * @param mass  Mass in kilograms.
     */
    protected Thing(double width, double depth, double mass) {
        this.width = width;
        this.depth = depth;
        this.mass = mass;
    }

    /**
     * The width of the thing in meter.
     *
     * @return the thing width in meter.
     */
    public double width() {
        return width;
    }

    /**
     * The depth of the thing in meter.
     *
     * @return the depth in meter.
     */
    public double depth() {
        return depth;
    }

    /**
     * The mass of the thing in kilograms.
     *
     * @return the mass in kilograms.
     */
    public double mass() {
        return mass;
    }
}
