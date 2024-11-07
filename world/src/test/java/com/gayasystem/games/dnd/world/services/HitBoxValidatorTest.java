package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.ThingA;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {HitBoxUtils.class, HitBoxValidator.class})
class HitBoxValidatorTest {
    @Autowired
    private HitBoxValidator validator;

    @Test
    void rotationOkWithFarThing() {
        var thingA = new ThingA(2, 1);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, new Velocity(0, 0, Point1S.ZERO));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, new Velocity(0, 0, Point1S.ZERO));

        var phi = validator.rotation(obj, other, Point1S.PI);

        assertEquals(PI, phi);
    }

    @Test
    void rotationOkWithCloseThing() {
        var thingA = new ThingA(20, 1);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, new Velocity(0, 0, Point1S.ZERO));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, Vector2D.of(2, 10), Point1S.ZERO, new Velocity(0, 0, Point1S.ZERO));

        var phi = validator.rotation(obj, other, Point1S.of(PI / 2));

        assertEquals(PI / 2, phi.getAzimuth());
    }

    @Test
    void rotationKo() {
        var thingA = new ThingA(20, 2);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, new Velocity(0, 0, Point1S.ZERO));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, Vector2D.of(10, 1), Point1S.ZERO, new Velocity(0, 0, Point1S.ZERO));

        var phi = validator.rotation(obj, other, Point1S.of(2 * PI));

        assertEquals(PI / 2, phi.getAzimuth());
    }

    @Test
    void translationAlone() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 1);
        var objA = new InGameObject(thingA, Vector2D.of(0, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = validator.translation(objA, objB, PolarCoordinates.of(10, PI / 4));
        PolarCoordinates expected = PolarCoordinates.of(10, PI / 4);
        assertEquals(expected, actual);
    }

    @Test
    void translationBlocked() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 1);
        var objA = new InGameObject(thingA, Vector2D.of(0, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = validator.translation(objA, objB, PolarCoordinates.of(20, 0));
        PolarCoordinates expected = PolarCoordinates.of(9, 0);
        assertEquals(expected, actual);
    }

    @Test
    void translationBlockedInDiagonalSquare() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 1);
        var objA = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = validator.translation(objA, objB, PolarCoordinates.of(20, PI / 4));
        PolarCoordinates expected = PolarCoordinates.of(sqrt((10 * 10) + (10 * 10)) - sqrt((1 * 1) + (1 * 1)), PI / 4);
        assertEquals(expected, actual);
    }

    @Test
    void translationBlockedInDiagonalSmallAgainstBig() {
        var thingA = new ThingA(1, 1);
        var thingB = new ThingA(1, 10);
        var objA = new InGameObject(thingA, Vector2D.of(0, 0), Point1S.ZERO, Velocity.NO_VELOCITY);
        var objB = new InGameObject(thingB, Vector2D.of(10, 10), Point1S.ZERO, Velocity.NO_VELOCITY);
        PolarCoordinates actual = validator.translation(objA, objB, PolarCoordinates.of(20, PI / 4));
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
        PolarCoordinates actual = validator.translation(objA, objB, PolarCoordinates.of(20, PI / 4));
        PolarCoordinates expected = PolarCoordinates.of(sqrt(200) - sqrt(2), PI / 4);
        assertEquals(expected.getRadius(), actual.getRadius(), 0.000000000000002);
        assertEquals(expected.getAzimuth(), actual.getAzimuth(), 0.000000000000002);
    }
}
