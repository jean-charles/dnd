package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;

public abstract class Thing implements Runnable {
    protected double width;
    protected double depth;
    protected double mass;

    /**
     * @param width thing width in feet.
     * @param depth thing depth in feet.
     * @param mass  thing mass in pounds.
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
