package com.gayasystem.games.dnd.common;

import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {
    @Test
    public void add() {
        var origin = new Orientation(PI / 4, PI / 4);
        var actual = origin.add(new Orientation(PI / 2, PI / 2));

        assertEquals(3 * PI / 4, actual.theta().doubleValue(), 0.0);
        assertEquals(3 * PI / 4, actual.phi().doubleValue(), 0.0);
    }

    @Test
    public void opposite() {
        var origin = new Orientation(PI / 4, PI / 4);
        var opposite = origin.opposite();

        assertEquals(5 * PI / 4, opposite.theta().doubleValue(), 0.0);
        assertEquals(5 * PI / 4, opposite.phi().doubleValue(), 0.0);
    }

    @Test
    public void transpose() {
        var origin = new Orientation(PI / 4, 0);
        var toBeTransposed = new Orientation(-PI / 4, 0);
        var expected = new Orientation(3 * PI / 2, 0);
        var transposed = origin.transpose(toBeTransposed);

        assertEquals(expected.theta().doubleValue(), transposed.theta().doubleValue(), 0.0);
        assertEquals(expected.phi().doubleValue(), transposed.phi().doubleValue(), 0.0);
    }
}