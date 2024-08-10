package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {
    private void assertFrom(double expectedX, double expectedY, Coordinate from, CircularCoordinate sc) {
        Coordinate c = from.from(sc);
        assertEquals(expectedX, c.x().doubleValue(), 0.000000000000001);
        assertEquals(expectedY, c.y().doubleValue(), 0.000000000000001);
    }

    private void assertTo(double expectedRho, double expectedPhi, Coordinate c) {
        CircularCoordinate circularCoordinate = c.to();
        assertEquals(expectedRho, circularCoordinate.rho().doubleValue(), 0.000000000000001);
        assertEquals(expectedPhi, circularCoordinate.orientation().phi().doubleValue(), 0.000000000000001);
    }

    @Test
    void assertFrom() {
        var c = new Coordinate(0, 0);
        assertFrom(1, 0, c, new CircularCoordinate(1, 0));
        assertFrom(0, 1, c, new CircularCoordinate(1, PI / 2));
        assertFrom(-1, 0, c, new CircularCoordinate(1, PI));
        assertFrom(0, -1, c, new CircularCoordinate(1, 3 * PI / 2));
    }

    @Test
    void to() {
        assertTo(1, 0, new Coordinate(1, 0));
        assertTo(1, PI / 2, new Coordinate(0, 1));
        assertTo(1, PI, new Coordinate(-1, 0));
        assertTo(1, -PI / 2, new Coordinate(0, -1));
    }

    @Test
    public void distance() {
        Coordinate c1 = new Coordinate(2, 2);
        Coordinate c2 = new Coordinate(4, 2);
        var distance = c1.distanceFrom(c2);
        assertEquals(2, distance);
    }
}