package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;

public abstract class Thing implements Moveable, Runnable {
    protected double width;
    protected double depth;
    protected double mass;
    private Velocity velocity;
    private Orientation rotation;

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
     * The mass of the thing in pound (lb).
     *
     * @return the mass in pound
     */
    public double mass() {
        return mass;
    }

    @Override
    public void velocity(Velocity velocity) {
        this.velocity = velocity;
    }

    @Override
    public void velocity(double speed, CircularCoordinate destination) {
        velocity = new Velocity(speed, destination);
    }

    @Override
    public Velocity velocity() {
        return velocity;
    }

    @Override
    public void rotation(Orientation orientation) {
        this.rotation = orientation;
    }

    @Override
    public Orientation rotation() {
        return rotation;
    }
}
