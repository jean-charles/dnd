package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(thing.velocity()).thenReturn(new Velocity(1, new SphericalCoordinate(10, 0, 0)));
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
