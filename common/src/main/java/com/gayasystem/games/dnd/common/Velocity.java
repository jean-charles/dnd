package com.gayasystem.games.dnd.common;

/**
 * @param speed        in meter per second.
 * @param acceleration acceleration in meter per second.
 * @param azimuth      angle of the direction in radian.
 */
public record Velocity(double speed, double acceleration, double azimuth) {
}
