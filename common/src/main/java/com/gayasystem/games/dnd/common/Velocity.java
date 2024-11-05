package com.gayasystem.games.dnd.common;


import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

/**
 * @param speed       in feet per second.
 * @param destination where to go.
 */
public record Velocity(double speed, PolarCoordinates destination) {
}
