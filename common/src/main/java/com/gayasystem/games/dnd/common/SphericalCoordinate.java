package com.gayasystem.games.dnd.common;

import java.math.BigDecimal;

import static java.lang.Math.PI;

public record SphericalCoordinate(BigDecimal rho, BigDecimal theta, BigDecimal phi) {
    public SphericalCoordinate(double rho, double theta, double phi) {
        this(BigDecimal.valueOf(rho), BigDecimal.valueOf(theta), BigDecimal.valueOf(phi));
    }

    public SphericalCoordinate add(SphericalCoordinate origin) {
        BigDecimal rho = this.rho.add(origin.rho);
        BigDecimal theta = this.theta.add(origin.theta);
        BigDecimal phi = this.phi.add(origin.phi);

        return new SphericalCoordinate(rho, theta, phi);
    }

    public SphericalCoordinate opposite() {
        return add(new SphericalCoordinate(0, PI, PI));
    }
}
