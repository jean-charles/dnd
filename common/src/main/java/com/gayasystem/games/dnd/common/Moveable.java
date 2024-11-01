package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;

public interface Moveable {
    /**
     * Set the velocity of the thing. Could be set by itself or external.
     *
     * @param velocity The velocity of the thing.
     */
    void velocity(Velocity velocity);

    /**
     * Set the velocity of the thing. Could be set by itself or external.
     *
     * @param speed The speed of the velocity in feet per hour.
     * @param destination The spherical coordinate destination.
     */
    void velocity(double speed, CircularCoordinate destination);

    /**
     * Current velocity of the thing.
     * @return {@link Velocity}
     */
    Velocity velocity();

    void rotation(Orientation orientation);

    Orientation rotation();
}
