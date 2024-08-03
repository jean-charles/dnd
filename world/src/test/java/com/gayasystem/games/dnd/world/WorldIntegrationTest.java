package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.ecosystem.beasts.Almiraj;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactoryImpl;
import com.gayasystem.games.dnd.lifeforms.brain.DefaultBrain;
import com.gayasystem.games.dnd.lifeforms.brain.memories.EngramComputing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = {WorldTestConfig.class, World.class, BrainFactoryImpl.class, DefaultBrain.class, EngramComputing.class, Almiraj.class, Carrot.class})

public class WorldIntegrationTest {
    @Autowired
    private World world;

    @Autowired
    private Almiraj almiraj;

    @Autowired
    private Carrot carrot;

    @Test
    public void integrationTests() {
        assertNotNull(almiraj);
        world.add(almiraj, new Coordinate(0, 0, 0), new Orientation(0, 0));
        assertNotNull(carrot);
        world.add(carrot, new Coordinate(10, 0, 0), new Orientation(0, 0));
        world.run();
        var almirajCoordinate = world.get(almiraj);
        assertNotNull(almirajCoordinate);
        assertEquals(new Coordinate(10, 0, 0), almirajCoordinate);
        var carrotCoordinate = world.get(carrot);
        assertNotNull(carrotCoordinate);
        assertEquals(new Coordinate(10, 0, 0), carrotCoordinate);
    }
}
