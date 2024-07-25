package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.SphericalCoordinate;

/**
 * @param speed       in feet per second
 * @param destination where to go, in spherical coordinate
 */
public record Velocity(double speed, SphericalCoordinate destination) {
}
