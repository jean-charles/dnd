package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.LifeFormA;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest(classes = {LifeFormTestConfig.class})
class BrainFactoryImplTest {
    static final double SPEED = 2.3;
    static final Map<Class<? extends Thing>, Emotion> MEMORIES = Map.of();

    @Autowired
    BrainFactory brainFactory;

    //    @Test
    void create() {
        LifeForm lifeForm = new LifeFormA();
        var brain = brainFactory.create(lifeForm, SPEED, MEMORIES);
        assertTrue(brain instanceof DefaultBrain);
    }
}