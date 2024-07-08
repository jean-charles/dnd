package com.gayasystem.games.dnd.common;

import java.math.BigDecimal;

import static java.lang.Math.PI;

/**
 * @param rho   distance in feet
 * @param theta longitudinal radian angle
 * @param phi   latitudinal radian angle
 */
public record SphericalCoordinate(BigDecimal rho, BigDecimal theta, BigDecimal phi) {
    public SphericalCoordinate(double rho, double theta, double phi) {
        this(BigDecimal.valueOf(rho), BigDecimal.valueOf(theta), BigDecimal.valueOf(phi));
    }

    public SphericalCoordinate(double theta, double phi) {
        this(BigDecimal.ZERO, BigDecimal.valueOf(theta), BigDecimal.valueOf(phi));
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

    public SphericalCoordinate transpose(SphericalCoordinate from) {
//        var fromRho = from.rho.doubleValue();
        var fromTheta = (from.theta.doubleValue() >= 0) ? from.theta : BigDecimal.valueOf(2 * PI + from.theta.doubleValue());
        var fromPhi = (from.phi.doubleValue() >= 0) ? from.phi : BigDecimal.valueOf(2 * PI + from.phi.doubleValue());

//        var transposedRho = BigDecimal.valueOf(abs(rho.doubleValue() - fromRho));
        var transposedTheta = fromTheta.subtract(this.theta);
        var transposedPhi = fromPhi.subtract(this.phi);

        return new SphericalCoordinate(BigDecimal.ZERO, transposedTheta, transposedPhi);
    }
}
