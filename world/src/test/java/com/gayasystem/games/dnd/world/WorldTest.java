package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@SpringJUnitConfig(classes = World.class)
public class WorldTest {
    @MockBean
    private Thing thing;

    @Autowired
    private World world;

    @Test
    public void runMove() {
        assertThat(thing).isNotNull();
        assertThat(world).isNotNull();
        when(thing.velocity()).thenReturn(new Velocity(1, new CircularCoordinate(10, 0)));
        double originalX = world.get(thing).x().doubleValue();

        world.run();
        verify(thing, times(1)).run();
        assertCoordinate(originalX, 0, 1, world.get(thing));

        world.run();
        verify(thing, times(2)).run();
        assertCoordinate(originalX, 0, 2, world.get(thing));
    }

    private void assertCoordinate(double expectedX, double expectedY, double expectedZ, Coordinate actual) {
        assertEquals(expectedX, actual.x().doubleValue(), 0.000000000000001);
        assertEquals(expectedY, actual.y().doubleValue(), 0.000000000000001);
    }

    @Test
    void addFrom() {
        Thing parent = new ThingA();
        Thing child = new ThingB();
        Orientation orientation = new Orientation(0);
        world.addFrom(parent, child, orientation);
    }

    @Test
    void add() {
        ThingA thing = new ThingA();
        Coordinate coordinate = new Coordinate(0, 0);
        Orientation orientation = new Orientation(0);

        world.add(thing, coordinate, orientation);

        Coordinate actual = world.get(thing);
        assertEquals(coordinate, actual);
    }

    @Test
    void addNulls() {
        try {
            world.add(null, new Coordinate(0, 0), new Orientation(0));
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
            world.add(new ThingA(), new Coordinate(0, 0), null);
            fail();
        } catch (Exception e) {
            assertEquals("Parameter 'orientation' is null!", e.getLocalizedMessage());
        }
    }
}
