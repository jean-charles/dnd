package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.coordinates.SphericalCoordinate;

import java.math.BigDecimal;

import static java.lang.Math.*;

public record Coordinate(BigDecimal x, BigDecimal y, BigDecimal z) {
    public Coordinate(double x, double y, double z) {
        this(BigDecimal.valueOf(x), BigDecimal.valueOf(y), BigDecimal.valueOf(z));
    }

    public Coordinate add(Coordinate relativeCoordinate) {
        var x = relativeCoordinate.x.doubleValue();
        var y = relativeCoordinate.y.doubleValue();
        var z = relativeCoordinate.z.doubleValue();
        x += this.x.doubleValue();
        y += this.y.doubleValue();
        z += this.z.doubleValue();
        return new Coordinate(x, y, z);
    }

    public static Coordinate from(SphericalCoordinate sc) {
        double rho = sc.rho().doubleValue();
        double theta = sc.orientation().theta().doubleValue();
        double phi = sc.orientation().phi().doubleValue();

        double x = rho * sin(phi) * cos(theta);
        double y = rho * sin(phi) * sin(theta);
        double z = rho * cos(phi);

        return new Coordinate(x, y, z);
    }

    public SphericalCoordinate to() {
        double x = this.x.doubleValue();
        double y = this.y.doubleValue();
        double z = this.z.doubleValue();

        double rho = sqrt(x * x + y * y + z * z);
        double theta = atan(y / x);
        double phi = acos(z / rho);

        return new SphericalCoordinate(rho, theta, phi);
    }

    public double distanceFrom(Coordinate formCoordinate) {
        var newX = pow(x.doubleValue() - formCoordinate.x.doubleValue(), 2);
        var newY = pow(y.doubleValue() - formCoordinate.y.doubleValue(), 2);
        var newZ = pow(z.doubleValue() - formCoordinate.z.doubleValue(), 2);

        return sqrt(newX + newY + newZ);
    }
}
