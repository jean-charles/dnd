package com.gayasystem.games.dnd.common.coordinates;

import java.math.BigDecimal;

/**
 * @param rho   distance in feet
 * @param orientation   spherical orientation
 */
public record SphericalCoordinate(BigDecimal rho, Orientation orientation) {
    public SphericalCoordinate(double rho, double phi) {
        this(BigDecimal.valueOf(rho), new Orientation(phi));
    }

    public SphericalCoordinate(double rho, Orientation orientation) {
        this(BigDecimal.valueOf(rho), orientation);
    }
}
