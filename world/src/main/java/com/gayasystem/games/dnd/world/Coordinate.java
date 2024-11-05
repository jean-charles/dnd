package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import org.apache.commons.geometry.euclidean.twod.Vector2D;

import java.math.BigDecimal;

import static java.lang.Math.*;

/**
 * Scalar coordinate in feet.
 *
 * @param x in feet.
 * @param y in feet.
 */
public record Coordinate(BigDecimal x, BigDecimal y) {
    public Coordinate(double x, double y) {
        this(BigDecimal.valueOf(x), BigDecimal.valueOf(y));
    }

    public Coordinate from(CircularCoordinate sc) {
        double rho = sc.rho().doubleValue();
        double phi = sc.orientation().phi().doubleValue();

        double x = this.x.doubleValue() + rho * cos(phi);
        double y = this.y.doubleValue() + rho * sin(phi);

        return new Coordinate(x, y);
    }

    public Vector2D toVector2D() {
        return Vector2D.of(x.doubleValue(), y.doubleValue());
    }

    public CircularCoordinate to() {
        return to(new Coordinate(0, 0));
    }

    public CircularCoordinate to(Coordinate coordinate) {
        double x = coordinate.x.subtract(this.x).doubleValue();
        double y = coordinate.y.subtract(this.y).doubleValue();

        double rho = hypot(x, y);
        double phi = atan2(y, x);
        return new CircularCoordinate(rho, phi);
    }

    public double distanceFrom(Coordinate formCoordinate) {
        var newX = pow(x.doubleValue() - formCoordinate.x.doubleValue(), 2);
        var newY = pow(y.doubleValue() - formCoordinate.y.doubleValue(), 2);

        return sqrt(newX + newY);
    }
}
