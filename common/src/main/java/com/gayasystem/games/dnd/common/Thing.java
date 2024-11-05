package com.gayasystem.games.dnd.common;

public abstract class Thing implements Runnable {
    protected double width;
    protected double depth;
    protected double mass;

    /**
     * @param width Distance between shoulders in feet.
     * @param depth Distance front to back in feet.
     * @param mass  Mass in pounds.
     */
    protected Thing(double width, double depth, double mass) {
        this.width = width;
        this.depth = depth;
        this.mass = mass;
    }

    /**
     * The width of the thing in feet.
     *
     * @return the thing width in feet.
     */
    public double width() {
        return width;
    }

    /**
     * The depth of the thing in feet.
     *
     * @return the depth in feet.
     */
    public double depth() {
        return depth;
    }

    /**
     * The mass of the thing in pounds (lb).
     *
     * @return the mass in pounds.
     */
    public double mass() {
        return mass;
    }
}
