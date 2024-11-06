package com.gayasystem.games.dnd.common;

import org.apache.commons.geometry.spherical.oned.Point1S;

/**
 * @param speed        in meter per second.
 * @param acceleration acceleration in meter per second.
 * @param azimuth      angle of the direction in radian.
 */
public record Velocity(double speed, double acceleration, Point1S azimuth) {
    public static final Velocity NO_VELOCITY = new Velocity(0, 0, Point1S.of(0));
}
