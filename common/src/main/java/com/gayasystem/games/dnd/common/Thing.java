package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;

public abstract class Thing implements Moveable, Runnable {
    protected double mass;
    private Velocity velocity;
    private Orientation rotation;

    protected Thing(double mass) {
        this.mass = mass;
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
