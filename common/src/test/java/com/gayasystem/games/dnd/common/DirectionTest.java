package com.gayasystem.games.dnd.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    @Test
    public void opposite() {
        var origin = new Direction(45, 45, 0);
        var opposite = origin.add(new Direction(180, 0, 0));
        assertEquals(new Direction(225, -45, 0), opposite);
    }
}