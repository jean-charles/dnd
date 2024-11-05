package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import com.gayasystem.games.dnd.ecosystem.beasts.Almiraj;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringJUnitConfig(classes = {WorldTestConfig.class, World.class, BrainFactoryImpl.class, DefaultBrain.class, EngramComputing.class, Almiraj.class, Carrot.class})
public class WorldIntegrationTest {
    @Autowired
    private World world;

    @Autowired
    private Almiraj almiraj;

    @Autowired
    private Carrot carrot;

    //    @Test
    public void integrationTests() {
        var velocity = new Velocity(0, PolarCoordinates.of(0, new Orientation(0)));
        assertNotNull(almiraj);
        world.add(almiraj, Vector2D.of(0, 0), new Orientation(0));
        assertNotNull(carrot);
        world.add(carrot, Vector2D.of(10, 0), new Orientation(0));
        world.run();
        var almirajCoordinate = world.getThingCoordinate(almiraj);
        assertNotNull(almirajCoordinate);
        assertEquals(Vector2D.of(10, 0), almirajCoordinate);
        var carrotCoordinate = world.getThingCoordinate(carrot);
        assertNotNull(carrotCoordinate);
        assertEquals(Vector2D.of(10, 0), carrotCoordinate);
    }
}
