package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;

import java.math.BigDecimal;

import static java.lang.Math.*;

public record Coordinate(BigDecimal x, BigDecimal y) {
    public Coordinate(double x, double y) {
        this(BigDecimal.valueOf(x), BigDecimal.valueOf(y));
    }

    public static Coordinate from(CircularCoordinate sc) {
        double rho = sc.rho().doubleValue();
        double phi = sc.orientation().phi().doubleValue();

        double x = rho * cos(phi);
        double y = rho * sin(phi);

        return new Coordinate(x, y);
    }

    public Coordinate add(Coordinate relativeCoordinate) {
        var x = relativeCoordinate.x.doubleValue();
        var y = relativeCoordinate.y.doubleValue();

        x += this.x.doubleValue();
        y += this.y.doubleValue();

        return new Coordinate(x, y);
    }

    public CircularCoordinate to() {
        double x = this.x.doubleValue();
        double y = this.y.doubleValue();

        double rho = sqrt(x * x + y * y);
        double phi = 0;
        if (x == 0 && y > 0)
            phi = PI / 2;
        else if (x == 0)
            phi = -PI / 2;
        else if (y == 0 && x > 0)
            phi = 0;
        else if (y == 0)
            phi = PI;
        else
            phi = atan(y / x);

        return new CircularCoordinate(rho, phi);
    }

    public double distanceFrom(Coordinate formCoordinate) {
        var newX = pow(x.doubleValue() - formCoordinate.x.doubleValue(), 2);
        var newY = pow(y.doubleValue() - formCoordinate.y.doubleValue(), 2);

        return sqrt(newX + newY);
    }
}
