package com.gayasystem.games.dnd.common;

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

    private class ThingA extends Thing {
        public ThingA(double mass) {
            super(0, 0, mass);
        }

        @Override
        public void run() {
        }
    }
}