package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.Orientation;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {
    @Test
    public void add() {
        var origin = new Orientation(PI / 4);
        var actual = origin.add(new Orientation(PI / 2));

        assertEquals(3 * PI / 4, actual.phi().doubleValue(), 0.0);
    }

    @Test
    public void opposite() {
        var origin = new Orientation(PI / 4);
        var opposite = origin.opposite();

        assertEquals(5 * PI / 4, opposite.phi().doubleValue(), 0.0);
    }

    @Test
    public void transposeWithPositiveValues() {
        var origin = new Orientation(PI / 4);
        var toBeTransposed = new Orientation(7 * PI / 4);
        var expected = new Orientation(-3 * PI / 2);
        var transposed = origin.transpose(toBeTransposed);

        assertEquals(expected.phi().doubleValue(), transposed.phi().doubleValue(), 0.0);
    }

    @Test
    public void transposeWithNegativeValues() {
        var origin = new Orientation(PI / 4);
        var toBeTransposed = new Orientation(-PI / 4);
        var expected = new Orientation(PI / 2);
        var transposed = origin.transpose(toBeTransposed);

        assertEquals(expected.phi().doubleValue(), transposed.phi().doubleValue(), 0.0);
    }
}