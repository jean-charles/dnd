package com.gayasystem.games.dnd.common;

import java.math.BigDecimal;

import static java.lang.Math.PI;

/**
 * @param theta longitudinal radian angle
 * @param phi   latitudinal radian angle
 */
public record Orientation(BigDecimal theta, BigDecimal phi) {
    public Orientation(double theta, double phi) {
        this(BigDecimal.valueOf(theta), BigDecimal.valueOf(phi));
    }

    public Orientation add(Orientation origin) {
        BigDecimal theta = this.theta().add(origin.theta());
        BigDecimal phi = this.phi().add(origin.phi());

        return new Orientation(theta, phi);
    }

    public Orientation opposite() {
        return add(new Orientation(PI, PI));
    }

    public Orientation transpose(Orientation from) {
        var fromTheta = (from.theta().doubleValue() >= 0) ? from.theta() : BigDecimal.valueOf(2 * PI + from.theta().doubleValue());
        var fromPhi = (from.phi().doubleValue() >= 0) ? from.phi() : BigDecimal.valueOf(2 * PI + from.phi().doubleValue());

        var transposedTheta = fromTheta.subtract(this.theta());
        var transposedPhi = fromPhi.subtract(this.phi());

        return new Orientation(transposedTheta, transposedPhi);
    }
}
