package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThingTest {

    @Test
    void mass() {
        var thing = new ThingA(1.2);
        var expected = 1.2;
        var actual = thing.mass();
        assertEquals(expected, actual);
    }

    @Test
    void velocity() {
        var thing = new ThingA(0.0);
        thing.velocity(1.2, new CircularCoordinate(1, 2));
        var expected = new Velocity(1.2, new CircularCoordinate(1, 2));
        var actual = thing.velocity();
        assertEquals(expected.speed(), actual.speed());
        assertEquals(expected.destination().rho(), actual.destination().rho());
        assertEquals(expected.destination().orientation().phi(), actual.destination().orientation().phi());
    }

    private class ThingA extends Thing {
        public ThingA(double mass) {
            super(0, 0, mass);
        }

        @Override
        public void run() {
        }
    }
}