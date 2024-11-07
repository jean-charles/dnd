package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.ThingA;
import com.gayasystem.games.dnd.world.services.domains.HitBox;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.junit.jupiter.api.Test;

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
        var velocity = new Velocity(0, 0, Point1S.ZERO);
        var center = Vector2D.of(10, 10);
        var obj = new InGameObject(thing, center, Point1S.ZERO, velocity);
        var actual = utils.hitBox(obj);
        var expected = new HitBox(
                center,
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
        var actual = utils.rotate(x, y, Point1S.PI, xOrig, yOrig);
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
        var actual = utils.rotate(x, y, Point1S.PI, xOrig, yOrig);
        var expected = Vector2D.of(0, 8);
        assertEquals(expected.getX(), actual.getX(), 0.00000000000001);
        assertEquals(expected.getY(), actual.getY(), 0.00000000000001);
    }

    @Test
    void minX() {
        var p1 = Vector2D.of(0, -1);
        var p2 = Vector2D.of(-1, 0);
        var p3 = Vector2D.of(0, 1);
        var p4 = Vector2D.of(1, 0);
        var actual = utils.minX(p1, p2, p3, p4);
        assertEquals(-1, actual, 0.00000000000001);
    }

    @Test
    void maxX() {
        var p1 = Vector2D.of(0, -1);
        var p2 = Vector2D.of(-1, 0);
        var p3 = Vector2D.of(0, 1);
        var p4 = Vector2D.of(1, 0);
        var actual = utils.maxX(p1, p2, p3, p4);
        assertEquals(1, actual, 0.00000000000001);
    }

    @Test
    void minY() {
        var p1 = Vector2D.of(0, -1);
        var p2 = Vector2D.of(-1, 0);
        var p3 = Vector2D.of(0, 1);
        var p4 = Vector2D.of(1, 0);
        var actual = utils.minY(p1, p2, p3, p4);
        assertEquals(-1, actual, 0.00000000000001);
    }

    @Test
    void maxY() {
        var p1 = Vector2D.of(0, -1);
        var p2 = Vector2D.of(-1, 0);
        var p3 = Vector2D.of(0, 1);
        var p4 = Vector2D.of(1, 0);
        var actual = utils.maxY(p1, p2, p3, p4);
        assertEquals(1, actual, 0.00000000000001);
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

    @Test
    void rotationOkWithFarThing() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 1);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var other = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);

        var phi = utils.rotation(obj, other, Point1S.PI);

        assertEquals(PI, phi.getAzimuth());
    }

    @Test
    void rotationOkWithCloseThing() {
        var thingA = new ThingA(20, 1);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, Vector2D.of(2, 10), Point1S.ZERO, Velocity.NO_VELOCITY);

        var phi = utils.rotation(obj, other, Point1S.of(PI / 2));

        assertEquals(PI / 2, phi.getAzimuth());
    }

    @Test
    void rotationKo() {
        var thingA = new ThingA(20, 2);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, Vector2D.of(10, 1), Point1S.ZERO, Velocity.NO_VELOCITY);

        var phi = utils.rotation(obj, other, Point1S.of(2 * PI));

        assertEquals(PI / 2, phi.getAzimuth());
    }

    @Test
    void translationAlone() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 1);
        var objA = new InGameObject(thingA, Vector2D.of(0, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = utils.translation(objA, objB, PolarCoordinates.of(10, PI / 4));
        PolarCoordinates expected = PolarCoordinates.of(10, PI / 4);
        assertEquals(expected, actual);
    }

    @Test
    void translationBlocked() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 1);
        var objA = new InGameObject(thingA, Vector2D.of(0, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = utils.translation(objA, objB, PolarCoordinates.of(20, 0));
        PolarCoordinates expected = PolarCoordinates.of(9, 0);
        assertEquals(expected, actual);
    }

    @Test
    void translationBlockedInDiagonalSquare() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 1);
        var objA = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = utils.translation(objA, objB, PolarCoordinates.of(20, PI / 4));
        PolarCoordinates expected = PolarCoordinates.of(sqrt(200) - sqrt(2), PI / 4);
        assertEquals(expected, actual);
    }

    @Test
    void translationBlockedInDiagonalSmallAgainstBig() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 10);
        var objA = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = utils.translation(objA, objB, PolarCoordinates.of(20, PI / 4));
        PolarCoordinates expected = PolarCoordinates.of(sqrt(200) - sqrt(2), PI / 4);
        assertEquals(expected.getRadius(), actual.getRadius(), 0.000000000000002);
        assertEquals(expected.getAzimuth(), actual.getAzimuth(), 0.000000000000002);
    }

    @Test
    void translationBlockedInDiagonalBigAgainstBig() {
        var thingA = new ThingA(1, 10);
        var thingB = new ThingA(1, 1);
        var objA = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = utils.translation(objA, objB, PolarCoordinates.of(20, PI / 4));
        PolarCoordinates expected = PolarCoordinates.of(sqrt(200) - sqrt(2), PI / 4);
        assertEquals(expected.getRadius(), actual.getRadius(), 0.000000000000002);
        assertEquals(expected.getAzimuth(), actual.getAzimuth(), 0.000000000000002);
    }
}