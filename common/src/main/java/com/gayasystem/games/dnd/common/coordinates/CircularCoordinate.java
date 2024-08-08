package com.gayasystem.games.dnd.common.coordinates;

import java.math.BigDecimal;

/**
 * @param rho   distance in feet
 * @param orientation   spherical orientation
 */
public record CircularCoordinate(BigDecimal rho, Orientation orientation) {
    public CircularCoordinate(double rho, double phi) {
        this(BigDecimal.valueOf(rho), new Orientation(phi));
    }

    public CircularCoordinate(double rho, Orientation orientation) {
        this(BigDecimal.valueOf(rho), orientation);
    }
}
