package com.gayasystem.games.dnd.world.services;

import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {PhysicalService.class})
class PhysicalServiceTest {
    @Autowired
    PhysicalService service;

    @Test
    void rotate() {
        var p = Point1S.of(PI / 4);
        var a = PI;
        Point1S nP = service.rotate(p, a);
        assertEquals(5 * PI / 4, nP.getAzimuth());
    }

    @Test
    void rotateOpposite() {
        var p = Point1S.of(PI / 4);
        var a = -PI;
        Point1S nP = service.rotate(p, a);
        assertEquals(5 * PI / 4, nP.getAzimuth());
    }

    @Test
    void move() {
        var c = Vector2D.of(10, 10);
        var d = PolarCoordinates.of(sqrt(2), PI / 4);
//        var v = service.move(c, d);
//        assertEquals(Vector2D.of(11, 11), v);
    }

    @Test
    void recalculateVelocity() {
    }

    @Test
    void distance() {
        var s = 60.0;
        var i = 0.5;
//        var d = service.distance(s, i);
//        assertEquals(30.0, d, 0.000000001);
    }

    @Test
    void slowingDown() {
    }
}