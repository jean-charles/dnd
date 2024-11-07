package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Disabled
@SpringJUnitConfig(classes = World.class)
public class WorldTest {
    @MockBean
    private Thing thing;

    @Autowired
    private World world;

    private void assertCoordinate(double expectedX, double expectedY, Vector2D actual) {
        assertEquals(expectedX, actual.getX(), 0.000000000000001);
        assertEquals(expectedY, actual.getY(), 0.000000000000001);
    }

    @Disabled
    @Test()
    public void runMove() {
        assertNotNull(thing);
        assertNotNull(world);
        double originalX = 0;
        world.add(thing, Vector2D.of(originalX, 0), 0);

        world.run();
        verify(thing, times(1)).run();
        assertCoordinate(originalX + 1, 0, world.getThingCoordinate(thing));

        world.run();
        verify(thing, times(2)).run();
        assertCoordinate(originalX + 2, 0, world.getThingCoordinate(thing));
    }

    @Disabled
    @Test
    void addFrom() {
        Thing parent = new ThingA();
        var velocity = new Velocity(0, 0, Point1S.ZERO);
        world.add(parent, Vector2D.of(0, 0), 0);
        Thing child = new ThingB();
        world.addFrom(parent, child, velocity);
    }

    @Disabled
    @Test
    void add() {
        ThingA thing = new ThingA();
        Vector2D coordinate = Vector2D.of(0, 0);

        world.add(thing, coordinate, 0);

        Vector2D actual = world.getThingCoordinate(thing);
        assertEquals(coordinate, actual);
    }
}
