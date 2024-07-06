package com.gayasystem.games.dnd.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphericalCoordinateTest {
    @Test
    public void add() {
        var origin = new SphericalCoordinate(0, 45, 45);
        var opposite = origin.add(new SphericalCoordinate(0, 90, 90));
        assertEquals(new SphericalCoordinate(0, 135, 135), opposite);
    }

    @Test
    public void opposite() {
        var origin = new SphericalCoordinate(0, 45, 45);
        var opposite = origin.opposite();
        assertEquals(new SphericalCoordinate(0, 225, 225), opposite);
    }
}