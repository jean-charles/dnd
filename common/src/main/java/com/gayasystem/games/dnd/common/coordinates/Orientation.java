package com.gayasystem.games.dnd.common.coordinates;

import java.math.BigDecimal;

import static java.lang.Math.PI;

/**
 * @param phi   latitudinal radian angle
 */
public record Orientation(BigDecimal phi) {
    private static final BigDecimal MAX_PHI = BigDecimal.valueOf(2 * PI);
    public Orientation(double phi) {
        this(BigDecimal.valueOf(phi));
    }

    public Orientation add(Orientation origin) {
        var phi = this.phi().add(origin.phi());
        if (phi.compareTo(MAX_PHI) > 1)
            phi = phi.subtract(MAX_PHI);
        return new Orientation(phi);
    }

    public Orientation opposite() {
        return add(new Orientation(PI));
    }

    public Orientation transpose(Orientation from) {
        var transposedPhi = phi.subtract(from.phi());
        return new Orientation(transposedPhi);
    }
}
