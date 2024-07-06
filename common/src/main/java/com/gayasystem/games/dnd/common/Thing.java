package com.gayasystem.games.dnd.common;

public abstract class Thing implements Moveable, Runnable {
    protected double mass;
    protected double displacement;
    private Velocity velocity;

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
    public void setDirection(SphericalCoordinate sphericalCoordinate) {
        velocity = new Velocity(displacement, sphericalCoordinate);
    }

    @Override
    public Velocity velocity() {
        return velocity;
    }
}
