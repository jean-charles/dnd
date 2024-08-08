package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;

/**
 * @param speed       in feet per second
 * @param destination where to go, in spherical coordinate
 */
public record Velocity(double speed, CircularCoordinate destination) {
}
