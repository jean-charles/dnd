package com.gayasystem.games.dnd.common;

import org.junit.jupiter.api.Test;

public class SpericalCoordinateTest {
    @Test
    public void opposite() {
        var origin = new SpericalCoordinate(45, 45, 0);
        var opposite = origin.add(new SpericalCoordinate(180, 0, 0));
        //assertEquals(new Direction(225, -45, 0), opposite);
    }
}