package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactoryImpl;
import com.gayasystem.games.dnd.lifeforms.brain.DefaultBrain;
import com.gayasystem.games.dnd.lifeforms.brain.memories.EngramComputing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {WorldTestConfig.class, World.class, BrainFactoryImpl.class, DefaultBrain.class, EngramComputing.class})
public class WorldIntegrationTest {
    @Autowired
    Thing thing;

    @Autowired
    private World world;

    @Test
    public void integrationTests() {
        var c = world.get(thing);
        world.run();
        assertEquals(c, world.get(thing));
    }
}
