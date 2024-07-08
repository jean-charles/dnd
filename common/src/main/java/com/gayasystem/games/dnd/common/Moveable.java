package com.gayasystem.games.dnd.common;

public interface Moveable {
    /**
     * Set the velocity of the thing. Could be set by itself or external.
     *
     * @param speed       The speed of the velocity in feet per hour.
     * @param destination The spherical coordinate destination.
     */
    void velocity(double speed, SphericalCoordinate destination);

    /**
     * Current velocity of the thing.
     * @return {@link Velocity}
     */
    Velocity velocity();
}
