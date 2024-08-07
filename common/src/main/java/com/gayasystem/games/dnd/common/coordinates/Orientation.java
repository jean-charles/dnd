package com.gayasystem.games.dnd.common.coordinates;

import java.math.BigDecimal;

import static java.lang.Math.PI;

/**
 * @param phi   latitudinal radian angle
 */
public record Orientation(BigDecimal phi) {
    public Orientation(double phi) {
        this(BigDecimal.valueOf(phi));
    }

    public Orientation add(Orientation origin) {
        BigDecimal phi = this.phi().add(origin.phi());
        return new Orientation(phi);
    }

    public Orientation opposite() {
        return add(new Orientation(PI));
    }

    public Orientation transpose(Orientation from) {
        var fromPhi = (from.phi().doubleValue() >= 0) ? from.phi() : BigDecimal.valueOf(2 * PI + from.phi().doubleValue());
        var transposedPhi = fromPhi.subtract(this.phi());
        return new Orientation(transposedPhi);
    }
}
