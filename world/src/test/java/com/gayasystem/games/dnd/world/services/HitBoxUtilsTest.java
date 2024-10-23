package com.gayasystem.games.dnd.world.services;

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
        var obj = new InGameObject(thing, new Coordinate(10, 10), new Orientation(0));
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
    void rotatePointWithO() {
        var c = new Coordinate(0, 0);
        var o = new Orientation(0);
        var x = BigDecimal.TEN;
        var y = BigDecimal.TWO;
        var actual = utils.rotate(c, o, x, y);
        var expected = new Point(BigDecimal.TEN, BigDecimal.TWO);
        assertEquals(expected.x().doubleValue(), actual.x().doubleValue(), 0.00000000000001);
        assertEquals(expected.y().doubleValue(), actual.y().doubleValue(), 0.00000000000001);
    }

    @Test
    void rotatePointWithHalfPI() {
        var c = new Coordinate(10, 5);
        var o = new Orientation(PI / 2);
        var x = BigDecimal.valueOf(7);
        var y = BigDecimal.valueOf(9);
        var actual = utils.rotate(c, o, x, y);
        assertEquals(6, actual.x().doubleValue(), 0.00000000000001);
        assertEquals(2, actual.y().doubleValue(), 0.00000000000001);
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