package com.gayasystem.games.dnd.world.services.domains;

import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HitBoxTest {
    @Test
    void convenianceConstructor() {
        var hb = new HitBox(Vector2D.of(0, 0), 10, 2);
        assertEquals(1, hb.p1().getX());
        assertEquals(5, hb.p1().getY());
        assertEquals(1, hb.p2().getX());
        assertEquals(-5, hb.p2().getY());
        assertEquals(-1, hb.p3().getX());
        assertEquals(-5, hb.p3().getY());
        assertEquals(-1, hb.p4().getX());
        assertEquals(5, hb.p4().getY());
    }
}