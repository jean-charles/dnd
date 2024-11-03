package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.ThingA;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {HitBoxUtils.class, HitBoxValidator.class})
class HitBoxValidatorTest {
    @Autowired
    private HitBoxValidator validator;

    @Test
    void rotationOk() {
        var thingA = new ThingA(2, 1);
        var obj = new InGameObject(thingA, new Coordinate(0,0), new Velocity(0, new CircularCoordinate(0, 0)));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, new Coordinate(10,10), new Velocity(0, new CircularCoordinate(0, 0)));

        var phi = validator.rotation(obj, other, PI);

        assertEquals(PI, phi);
    }

    @Test
    void rotationKo() {
        var thingA = new ThingA(20, 2);
        var obj = new InGameObject(thingA, new Coordinate(0,0), new Velocity(0, new CircularCoordinate(0, 0)));
        var thingB = new ThingA(1, 1);
        var other = new InGameObject(thingB, new Coordinate(10,1), new Velocity(0, new CircularCoordinate(0, 0)));

        var phi = validator.rotation(obj, other, 2 * PI);

        assertEquals(PI, phi);
    }
}