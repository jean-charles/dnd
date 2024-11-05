package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.ThingA;
import org.apache.commons.geometry.euclidean.twod.Line;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.shape.Circle;
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
        var obj = new InGameObject(thingA, new Coordinate(0, 0), new Velocity(0, new CircularCoordinate(0, 0)));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, new Coordinate(10, 10), new Velocity(0, new CircularCoordinate(0, 0)));

        var phi = validator.rotation(obj, other, PI);

        assertEquals(PI, phi);
    }

    @Test
    void rotationOkWithCloseThing() {
        var thingA = new ThingA(20, 1);
        var obj = new InGameObject(thingA, new Coordinate(0, 0), new Velocity(0, new CircularCoordinate(0, 0)));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, new Coordinate(2, 10), new Velocity(0, new CircularCoordinate(0, 0)));

        var phi = validator.rotation(obj, other, PI / 2);

        assertEquals(PI / 2, phi);
    }

    @Test
    void rotationKo() {
        var thingA = new ThingA(20, 2);
        var obj = new InGameObject(thingA, new Coordinate(0, 0), new Velocity(0, new CircularCoordinate(0, 0)));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, new Coordinate(10, 1), new Velocity(0, new CircularCoordinate(0, 0)));

        var phi = validator.rotation(obj, other, 2 * PI);

        assertEquals(PI / 2, phi);
    }

    @Test
    void geometry1() {
        Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-6);
        Circle c = Circle.from(Vector2D.ZERO, 2, precision);
        Line l = Lines.fromPoints(Vector2D.of(0, 4), Vector2D.of(4, 0), precision);

        var i = c.firstIntersection(l);

    }

    @Test
    void geometry2() {
        Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-6);
        Circle c = Circle.from(Vector2D.ZERO, 2, precision);
        Line l = Lines.fromPoints(Vector2D.of(-2, 2), Vector2D.of(2, -2), precision);

        var i = c.firstIntersection(l);

        assertNotNull(i);
        assertEquals(-1.414213, i.getX(), 0.0001);
        assertEquals(1.414213, i.getY(), 0.0001);
    }

    @Test
    void geometry3() {
        Precision.DoubleEquivalence precision = Precision.doubleEquivalenceOfEpsilon(1e-6);
        Circle c = Circle.from(Vector2D.ZERO, 2, precision);
        Line l = Lines.fromPoints(Vector2D.of(-2, 2), Vector2D.of(2, -2), precision);

        var i = c.intersections(l);

        assertNotNull(i);
        assertEquals(2, i.size());
        assertEquals(-1.414213, i.get(0).getX(), 0.0001);
        assertEquals(1.414213, i.get(0).getY(), 0.0001);
        assertEquals(1.414213, i.get(1).getX(), 0.0001);
        assertEquals(-1.414213, i.get(1).getY(), 0.0001);
    }
}
