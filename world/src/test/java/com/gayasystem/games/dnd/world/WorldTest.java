package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig(classes = World.class)
public class WorldTest {
    @MockBean
    private Thing thing;

    @Autowired
    private World world;

    private void assertCoordinate(double expectedX, double expectedY, Vector2D actual) {
        assertEquals(expectedX, actual.getX().doubleValue(), 0.000000000000001);
        assertEquals(expectedY, actual.getY(), 0.000000000000001);
    }

    @Test()
    public void runMove() {
        assertNotNull(thing);
        assertNotNull(world);
        double originalX = 0;
        world.add(thing, Vector2D.of(originalX, 0), new Orientation(0));

        world.run();
        verify(thing, times(1)).run();
        assertCoordinate(originalX + 1, 0, world.getThingCoordinate(thing));

        world.run();
        verify(thing, times(2)).run();
        assertCoordinate(originalX + 2, 0, world.getThingCoordinate(thing));
    }

    @Test
    void addFrom() {
        Thing parent = new ThingA();
        var velocity = new Velocity(0, PolarCoordinates.of(0, new Orientation(0)));
        world.add(parent, Vector2D.of(0, 0), new Orientation(0));
        Thing child = new ThingB();
        world.addFrom(parent, child, velocity);
    }

    @Test
    void add() {
        ThingA thing = new ThingA();
        Vector2D coordinate = Vector2D.of(0, 0);

        world.add(thing, coordinate, new Orientation(0));

        Vector2D actual = world.getThingCoordinate(thing);
        assertEquals(coordinate, actual);
    }

    @Test
    void addNulls() {
        try {
            world.add(null, Vector2D.of(0, 0), new Orientation(0));
            fail();
        } catch (Exception e) {
            assertEquals("Parameter 'thing' is null!", e.getLocalizedMessage());
        }
        try {
            world.add(new ThingA(), null, new Orientation(0));
            fail();
        } catch (Exception e) {
            assertEquals("Parameter 'coordinate' is null!", e.getLocalizedMessage());
        }
        try {
            world.add(new ThingA(), Vector2D.of(0, 0), null);
            fail();
        } catch (Exception e) {
            assertEquals("Parameter 'orientation' is null!", e.getLocalizedMessage());
        }
    }
}
