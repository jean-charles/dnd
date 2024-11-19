package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.ecosystem.beasts.Almiraj;
import com.gayasystem.games.dnd.ecosystem.houses.Wall;
import com.gayasystem.games.dnd.world.ThingA;
import com.gayasystem.games.dnd.world.services.domains.HitBox;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(classes = {HitBoxUtils.class})
public class HitBoxUtilsTest {
    private static final double PRECISION = 0.0000000000001;

    @Autowired
    HitBoxUtils utils = new HitBoxUtils();

    /**
     * Create expected polar coordinates only for horizontal translation.
     *
     * @param o1
     * @param o2
     * @return
     */
    private PolarCoordinates expected(InGameObject o1, InGameObject o2) {
        var depth1 = o1.thing().depth();
        var depth2 = o2.thing().depth();
        var x1 = o1.coordinate().getX();
        var x2 = o2.coordinate().getX();
        return PolarCoordinates.of(x2 - x1 - depth1 / 2 - depth2 / 2, 0);
    }

    @Test
    void hitBox() {
        var thing = new ThingA(100, 20);
        var center = Vector2D.of(10, 10);
        var obj = new InGameObject(thing, center, Point1S.ZERO, Velocity.NO_VELOCITY);
        var actual = utils.hitBox(obj);
        var expected = new HitBox(
                center,
                Vector2D.of(20, 60),
                Vector2D.of(20, -40),
                Vector2D.of(0, -40),
                Vector2D.of(0, 60)
        );
        assertEquals(expected.p1().getX(), actual.p1().getX(), PRECISION);
        assertEquals(expected.p1().getY(), actual.p1().getY(), PRECISION);
        assertEquals(expected.p2().getX(), actual.p2().getX(), PRECISION);
        assertEquals(expected.p2().getY(), actual.p2().getY(), PRECISION);
        assertEquals(expected.p3().getX(), actual.p3().getX(), PRECISION);
        assertEquals(expected.p3().getY(), actual.p3().getY(), PRECISION);
        assertEquals(expected.p4().getX(), actual.p4().getX(), PRECISION);
        assertEquals(expected.p4().getY(), actual.p4().getY(), PRECISION);
    }

    @Test
    void hitBoxWithRotation() {
        var thing = new ThingA(100, 20);
        var center = Vector2D.of(10, 10);
        var obj = new InGameObject(thing, center, Point1S.of(PI / 4), Velocity.NO_VELOCITY);
        var actual = utils.hitBox(obj);
        var expected = new HitBox(
                center,
                Vector2D.of(20, 60),
                Vector2D.of(20, -40),
                Vector2D.of(0, -40),
                Vector2D.of(0, 60)
        );
        assertEquals(expected.p1().getX(), actual.p1().getX(), PRECISION);
        assertEquals(expected.p1().getY(), actual.p1().getY(), PRECISION);
        assertEquals(expected.p2().getX(), actual.p2().getX(), PRECISION);
        assertEquals(expected.p2().getY(), actual.p2().getY(), PRECISION);
        assertEquals(expected.p3().getX(), actual.p3().getX(), PRECISION);
        assertEquals(expected.p3().getY(), actual.p3().getY(), PRECISION);
        assertEquals(expected.p4().getX(), actual.p4().getX(), PRECISION);
        assertEquals(expected.p4().getY(), actual.p4().getY(), PRECISION);
    }

    @Test
    void rotatePointAtCenterOfPi() {
        var x = 0.0;
        var y = 0.0;
        var xOrig = 10.0;
        var yOrig = 2.0;
        var actual = utils.rotate(x, y, Point1S.PI, xOrig, yOrig);
        var expected = Vector2D.of(-10, -2);
        assertEquals(expected.getX(), actual.getX(), PRECISION);
        assertEquals(expected.getY(), actual.getY(), PRECISION);
    }

    @Test
    void rotatePointOffCenterOfHalfPi() {
        var x = 10.0;
        var y = 10.0;
        var xOrig = x + 10.0;
        var yOrig = y + 2.0;
        var actual = utils.rotate(x, y, Point1S.PI, xOrig, yOrig);
        var expected = Vector2D.of(0, 8);
        assertEquals(expected.getX(), actual.getX(), PRECISION);
        assertEquals(expected.getY(), actual.getY(), PRECISION);
    }

    @Test
    void rotateFrontLeft() {
        var actual = utils.rotateFrontLeft(0, 0, Point1S.PI, 5, 5);
        Vector2D expected = Vector2D.of(-5, -5);
        assertEquals(expected.getX(), actual.getX(), PRECISION);
        assertEquals(expected.getY(), actual.getY(), PRECISION);
    }

    @Test
    void rotateFrontRight() {
        var actual = utils.rotateFrontRight(0, 0, Point1S.PI, 5, 5);
        Vector2D expected = Vector2D.of(-5, 5);
        assertEquals(expected.getX(), actual.getX(), PRECISION);
        assertEquals(expected.getY(), actual.getY(), PRECISION);
    }

    @Test
    void rotateRearRight() {
        var actual = utils.rotateRearRight(0, 0, Point1S.PI, 5, 5);
        Vector2D expected = Vector2D.of(5, 5);
        assertEquals(expected.getX(), actual.getX(), PRECISION);
        assertEquals(expected.getY(), actual.getY(), PRECISION);
    }

    @Test
    void rotateRearLeft() {
        var actual = utils.rotateRearLeft(0, 0, Point1S.PI, 5, 5);
        Vector2D expected = Vector2D.of(5, -5);
        assertEquals(expected.getX(), actual.getX(), PRECISION);
        assertEquals(expected.getY(), actual.getY(), PRECISION);
    }

    @Test
    void minX() {
        var p1 = Vector2D.of(0, -1);
        var p2 = Vector2D.of(-1, 0);
        var p3 = Vector2D.of(0, 1);
        var p4 = Vector2D.of(1, 0);
        var actual = utils.minX(p1, p2, p3, p4);
        assertEquals(-1, actual, PRECISION);
    }

    @Test
    void maxX() {
        var p1 = Vector2D.of(0, -1);
        var p2 = Vector2D.of(-1, 0);
        var p3 = Vector2D.of(0, 1);
        var p4 = Vector2D.of(1, 0);
        var actual = utils.maxX(p1, p2, p3, p4);
        assertEquals(1, actual, PRECISION);
    }

    @Test
    void minY() {
        var p1 = Vector2D.of(0, -1);
        var p2 = Vector2D.of(-1, 0);
        var p3 = Vector2D.of(0, 1);
        var p4 = Vector2D.of(1, 0);
        var actual = utils.minY(p1, p2, p3, p4);
        assertEquals(-1, actual, PRECISION);
    }

    @Test
    void maxY() {
        var p1 = Vector2D.of(0, -1);
        var p2 = Vector2D.of(-1, 0);
        var p3 = Vector2D.of(0, 1);
        var p4 = Vector2D.of(1, 0);
        var actual = utils.maxY(p1, p2, p3, p4);
        assertEquals(1, actual, PRECISION);
    }

    @Test
    void couldItCrossIt() {
        var hb1 = new HitBox(Vector2D.of(0, 0), 20, 2);
        var hb2 = new HitBox(Vector2D.of(4, 4), 2, 2);
        var actual = utils.couldItCrossIt(hb1, hb2);
        assertTrue(actual);
    }

    @Test
    void azimuthWhereItCross() {
        HitBox hb1 = new HitBox(Vector2D.of(0, 0), 20, 1);
        HitBox hb2 = new HitBox(Vector2D.of(5, 1), 2, 1);
        double actual = utils.azimuthWhereItCross(hb1, hb2, PI);
        assertEquals(PI / 2, actual, PRECISION);
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
        PolarCoordinates actual = utils.translation(objA, objB, PolarCoordinates.of(200, 0));
        PolarCoordinates expected = expected(objA, objB);
        assertEquals(expected, actual);
    }

    @Test
    void translationBlockedNegative() {
        var thingA = new ThingA(1.2, 1.2);
        var thingB = new ThingA(1.4, 1.4);
        var objA = new InGameObject(thingA, Vector2D.of(-4.5, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(5.5, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = utils.translation(objA, objB, PolarCoordinates.of(200, 0));
        PolarCoordinates expected = expected(objA, objB);
        assertEquals(expected.getAzimuth(), actual.getAzimuth(), 0.0000000001);
        assertEquals(expected.getRadius(), actual.getRadius(), 0.0000000001);
    }

    @Test
    void translationGame() {
        var thingA = new Almiraj();
        var thingB = new Wall(1.0, 0.05);
        var objA = new InGameObject(thingA, Vector2D.of(-1.2, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(2, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = utils.translation(objA, objB, PolarCoordinates.of(200, 0));
        PolarCoordinates expected = expected(objA, objB);
        assertEquals(expected.getAzimuth(), actual.getAzimuth(), 0.0000000001);
        assertEquals(expected.getRadius(), actual.getRadius(), 0.0000000001);
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