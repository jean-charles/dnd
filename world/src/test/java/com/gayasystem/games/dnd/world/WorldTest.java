package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WorldTest {
    @Test
    public void runMove() {
        var thing = mock(Thing.class);
        when(thing.velocity()).thenReturn(new Velocity(1, new SphericalCoordinate(10, 0, 0)));
        Collection<Thing> things = List.of(thing);
        var world = new World(things);
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
        assertEquals(expectedZ, actual.z().doubleValue(), 0.000000000000001);
    }
}