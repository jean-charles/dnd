package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.ThingA;
import com.gayasystem.games.dnd.world.services.domains.HitBox;
import com.gayasystem.games.dnd.world.services.domains.Point;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HitBoxUtilsTest {
    HitBoxUtils utils = new HitBoxUtils();

    @Test
    void alignedRectangle() {
    }

    @Test
    void hitBoxWithoutRotation() {
        var thing = new ThingA(100, 20);
        var velocity = new Velocity(0, PolarCoordinates.of(0, 0));
        var center = Vector2D.of(10, 10);
        var obj = new InGameObject(thing, center, velocity);
        var actual = utils.hitBox(obj);
        var expected = new HitBox(
                center.toVector2D(),
                Vector2D.of(20, 60),
                Vector2D.of(20, -40),
                Vector2D.of(0, -40),
                Vector2D.of(0, 60)
        );
        assertEquals(expected.p1().getX(), actual.p1().getX(), 0.0000000000001);
        assertEquals(expected.p1().getY(), actual.p1().getY(), 0.0000000000001);
        assertEquals(expected.p2().getX(), actual.p2().getX(), 0.0000000000001);
        assertEquals(expected.p2().getY(), actual.p2().getY(), 0.0000000000001);
        assertEquals(expected.p3().getX(), actual.p3().getX(), 0.0000000000001);
        assertEquals(expected.p3().getY(), actual.p3().getY(), 0.0000000000001);
        assertEquals(expected.p4().getX(), actual.p4().getX(), 0.0000000000001);
        assertEquals(expected.p4().getY(), actual.p4().getY(), 0.0000000000001);
    }

    @Test
    void rotatePointAtCenterOfPi() {
        var x = 0.0;
        var y = 0.0;
        var xOrig = 10.0;
        var yOrig = 2.0;
        var actual = utils.rotate(x, y, PI, xOrig, yOrig);
        var expected = Vector2D.of(-10, -2);
        assertEquals(expected.getX(), actual.getX(), 0.00000000000001);
        assertEquals(expected.getY(), actual.getY(), 0.00000000000001);
    }

    @Test
    void rotatePointOfHalfPi() {
        var x = 10.0;
        var y = 10.0;
        var xOrig = x + 10.0;
        var yOrig = y + 2.0;
        var actual = utils.rotate(x, y, PI, xOrig, yOrig);
        var expected = Vector2D.of(0, 8);
        assertEquals(expected.getX(), actual.getX(), 0.00000000000001);
        assertEquals(expected.getY(), actual.getY(), 0.00000000000001);
    }

    @Test
    void minX() {
        var p1 = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(-1));
        var p2 = new Point(BigDecimal.valueOf(-1), BigDecimal.valueOf(0));
        var p3 = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(1));
        var p4 = new Point(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        BigDecimal actual = utils.minX(p1, p2, p3, p4);
        assertEquals(BigDecimal.valueOf(-1), actual);
    }

    @Test
    void maxX() {
        var p1 = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(-1));
        var p2 = new Point(BigDecimal.valueOf(-1), BigDecimal.valueOf(0));
        var p3 = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(1));
        var p4 = new Point(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        BigDecimal actual = utils.maxX(p1, p2, p3, p4);
        assertEquals(BigDecimal.valueOf(1), actual);
    }

    @Test
    void minY() {
        var p1 = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(-1));
        var p2 = new Point(BigDecimal.valueOf(-1), BigDecimal.valueOf(0));
        var p3 = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(1));
        var p4 = new Point(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        BigDecimal actual = utils.minY(p1, p2, p3, p4);
        assertEquals(BigDecimal.valueOf(-1), actual);
    }

    @Test
    void maxY() {
        var p1 = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(-1));
        var p2 = new Point(BigDecimal.valueOf(-1), BigDecimal.valueOf(0));
        var p3 = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(1));
        var p4 = new Point(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        BigDecimal actual = utils.maxY(p1, p2, p3, p4);
        assertEquals(BigDecimal.valueOf(1), actual);
    }

    @Test
    void radius() {
        var hb = new HitBox(
                Vector2D.of(0, 0),
                Vector2D.of(1, 1),
                Vector2D.of(1, -1),
                Vector2D.of(-1, -1),
                Vector2D.of(-1, 1));
        var actual = utils.radius(hb);
        assertEquals(sqrt(2), actual, 0.0000000001);
    }

    @Test
    void couldItCrossIt() {
        var hb1 = new HitBox(Vector2D.of(0, 0), 20, 2);
        var hb2 = new HitBox(Vector2D.of(4, 4), 2, 2);
        var actual = utils.couldItCrossIt(hb1, hb2);
        assertTrue(actual);
    }
}