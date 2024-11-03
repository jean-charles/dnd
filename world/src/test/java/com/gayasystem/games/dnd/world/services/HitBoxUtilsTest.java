package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.ThingA;
import com.gayasystem.games.dnd.world.services.domains.HitBox;
import com.gayasystem.games.dnd.world.services.domains.Point;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HitBoxUtilsTest {
    HitBoxUtils utils = new HitBoxUtils();

    @Test
    void alignedRectangle() {
    }

    @Test
    void hitBoxWithoutRotation() {
        var thing = new ThingA(100, 20);
        var velocity = new Velocity(0, new CircularCoordinate(0, 0));
        var obj = new InGameObject(thing, new Coordinate(10, 10), velocity);
        var actual = utils.hitBox(obj);
        var expected = new HitBox(
                new Point(BigDecimal.valueOf(20), BigDecimal.valueOf(60)),
                new Point(BigDecimal.valueOf(20), BigDecimal.valueOf(-40)),
                new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(-40)),
                new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(60))
        );
        assertEquals(expected.p1().x().doubleValue(), actual.p1().x().doubleValue(), 0.0000000000001);
        assertEquals(expected.p1().y().doubleValue(), actual.p1().y().doubleValue(), 0.0000000000001);
        assertEquals(expected.p2().x().doubleValue(), actual.p2().x().doubleValue(), 0.0000000000001);
        assertEquals(expected.p2().y().doubleValue(), actual.p2().y().doubleValue(), 0.0000000000001);
        assertEquals(expected.p3().x().doubleValue(), actual.p3().x().doubleValue(), 0.0000000000001);
        assertEquals(expected.p3().y().doubleValue(), actual.p3().y().doubleValue(), 0.0000000000001);
        assertEquals(expected.p4().x().doubleValue(), actual.p4().x().doubleValue(), 0.0000000000001);
        assertEquals(expected.p4().y().doubleValue(), actual.p4().y().doubleValue(), 0.0000000000001);
    }

    @Test
    void rotatePointAtCenterOfPi() {
        var x = 0.0;
        var y = 0.0;
        var xOrig = 10.0;
        var yOrig = 2.0;
        var actual = utils.rotate(x, y, PI, xOrig, yOrig);
        var expected = new Point(BigDecimal.valueOf(-10), BigDecimal.valueOf(-2));
        assertEquals(expected.x().doubleValue(), actual.x().doubleValue(), 0.00000000000001);
        assertEquals(expected.y().doubleValue(), actual.y().doubleValue(), 0.00000000000001);
    }

    @Test
    void rotatePointOfHalfPi() {
        var x = 10.0;
        var y = 10.0;
        var xOrig = x + 10.0;
        var yOrig = y + 2.0;
        var actual = utils.rotate(x, y, PI, xOrig, yOrig);
        var expected = new Point(BigDecimal.valueOf(0), BigDecimal.valueOf(8));
        assertEquals(expected.x().doubleValue(), actual.x().doubleValue(), 0.00000000000001);
        assertEquals(expected.y().doubleValue(), actual.y().doubleValue(), 0.00000000000001);
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
}