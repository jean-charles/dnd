package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {
    @Test
    void from() {
        CircularCoordinate sc = new CircularCoordinate(8, PI / 3);
        Coordinate c = Coordinate.from(sc);

        assertEquals(2, c.x().doubleValue(), 0.0);
        assertEquals(2 * sqrt(3), c.y().doubleValue(), 0.0000000000001);
    }

    @Test
    void to() {
        Coordinate c = new Coordinate(2, 2 * sqrt(3));
        CircularCoordinate circularCoordinate = c.to();
        assertEquals(8, circularCoordinate.rho().doubleValue(), 0.000000000000001);
        assertEquals(PI / 6, circularCoordinate.orientation().phi().doubleValue(), 0.0000000000000002);
    }

    @Test
    public void distance() {
        Coordinate c1 = new Coordinate(2, 2);
        Coordinate c2 = new Coordinate(4, 2);
        var distance = c1.distanceFrom(c2);
        assertEquals(2, distance);
    }
}