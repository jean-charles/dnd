package com.gayasystem.games.dnd.world;

import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {
    private void assertFrom(double expectedX, double expectedY, Vector2D from, PolarCoordinates sc) {
        Vector2D c = from.from(sc);
        assertEquals(expectedX, c.getX().doubleValue(), 0.000000000000001);
        assertEquals(expectedY, c.getY(), 0.000000000000001);
    }

    private void assertTo(double expectedRho, double expectedPhi, Vector2D c) {
        PolarCoordinates PolarCoordinates = c.to();
        assertEquals(expectedRho, PolarCoordinates.rho().doubleValue(), 0.000000000000001);
        assertEquals(expectedPhi, PolarCoordinates.orientation().phi().doubleValue(), 0.000000000000001);
    }

    private void assertTo(double expectedRho, double expectedPhi, PolarCoordinates actual) {
        assertEquals(expectedRho, actual.rho().doubleValue(), 0.000000000000001);
        assertEquals(expectedPhi, actual.orientation().phi().doubleValue(), 0.000000000000001);
    }

    @Test
    void assertFrom() {
        var c = Vector2D.of(0, 0);
        assertFrom(1, 0, c, PolarCoordinates.of(1, 0));
        assertFrom(0, 1, c, PolarCoordinates.of(1, PI / 2));
        assertFrom(-1, 0, c, PolarCoordinates.of(1, PI));
        assertFrom(0, -1, c, PolarCoordinates.of(1, 3 * PI / 2));
    }

    @Test
    void toCoordinates() {
        var from = Vector2D.of(1, 1);
        var to = Vector2D.of(2, 2);
        assertTo(sqrt(2), PI / 4, from.to(to));

        from = Vector2D.of(2, 2);
        to = Vector2D.of(1, 1);
        assertTo(sqrt(2), -3 * PI / 4, from.to(to));

        from = Vector2D.of(950, 0);
        to = Vector2D.of(960, 0);
        assertTo(10, 0, from.to(to));
    }

    @Test
    void to() {
        assertTo(1, 0, Vector2D.of(1, 0));
        assertTo(1, PI / 2, Vector2D.of(0, 1));
        assertTo(1, PI, Vector2D.of(-1, 0));
        assertTo(1, -PI / 2, Vector2D.of(0, -1));
    }

    @Test
    public void distance() {
        Vector2D c1 = Vector2D.of(2, 2);
        Vector2D c2 = Vector2D.of(4, 2);
        var distance = c1.distance(c2);
        assertEquals(2, distance);
    }
}