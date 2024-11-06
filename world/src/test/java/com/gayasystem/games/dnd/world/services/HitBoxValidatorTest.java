package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.ThingA;
import org.apache.commons.geometry.euclidean.twod.Line;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.shape.Circle;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.geometry.spherical.oned.Transform1S;
import org.apache.commons.numbers.core.Precision;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = {HitBoxUtils.class, HitBoxValidator.class})
class HitBoxValidatorTest {
    @Autowired
    private HitBoxValidator validator;

    @Test
    void rotationOkWithFarThing() {
        var thingA = new ThingA(2, 1);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), new Velocity(0, 0, Point1S.of(0)));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, Vector2D.of(10, 10), new Velocity(0, 0, Point1S.of(0)));

        var phi = validator.rotation(obj, other, Point1S.PI);

        assertEquals(PI, phi);
    }

    @Test
    void rotationOkWithCloseThing() {
        var thingA = new ThingA(20, 1);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), new Velocity(0, 0, Point1S.of(0)));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, Vector2D.of(2, 10), new Velocity(0, 0, Point1S.of(0)));

        var phi = validator.rotation(obj, other, Point1S.of(PI / 2));

        assertEquals(PI / 2, phi.getAzimuth());
    }

    @Test
    void rotationKo() {
        var thingA = new ThingA(20, 2);
        var obj = new InGameObject(thingA, Vector2D.of(0, 0), new Velocity(0, 0, Point1S.of(0)));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, Vector2D.of(10, 1), new Velocity(0, 0, Point1S.of(0)));

        var phi = validator.rotation(obj, other, Point1S.of(2 * PI));

        assertEquals(PI / 2, phi.getAzimuth());
    }

    @Test
    void geometry1() {
        var p1 = Point1S.ZERO;
        var rotation1 = Point1S.of(PI / 2);
        var actual1 = Point1S.of(p1.signedDistance(rotation1));
        assertEquals(PI / 2, actual1.getAzimuth());

        var t2 = Transform1S.createRotation(PI / 4);
        var p2 = Point1S.of(PI / 4);
        var actual2 = t2.apply(p2);
        assertEquals(PI / 2, actual2.getAzimuth());

        var t3 = Transform1S.createRotation(-PI / 4 - 2 * PI);
        var p3 = Point1S.of(PI / 4);
        var actual3 = t3.apply(p3).getNormalizedAzimuth();
        assertEquals(0, actual3);

        var t4 = Transform1S.createRotation(-PI / 4);
        var p4 = Point1S.of(PI / 4);
        var actual4 = t4.rotate(2 * PI).apply(p4).getNormalizedAzimuth();
        assertEquals(0, actual4);
    }

    @Test
    void geometry2() {
        var c = Vector2D.of(10, 10);
        var t = PolarCoordinates.of(5, PI / 2);
        var newCoordinate = c.add(t.toCartesian());
        assertEquals(Vector2D.of(10, 15), newCoordinate);
    }
}