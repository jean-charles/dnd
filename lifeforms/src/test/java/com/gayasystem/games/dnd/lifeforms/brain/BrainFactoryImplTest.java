package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.LifeFormA;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactoryImpl;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.DefaultBrain;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(classes = {BrainFactoryImpl.class, DefaultBrain.class, EngramComputing.class})
class BrainFactoryImplTest {
    static final double SPEED = 2.3;
    static final Map<Class<? extends Thing>, Emotion> MEMORIES = Map.of();

    @Autowired
    BrainFactory brainFactory;

    @Test
    void create() {
        LifeForm lifeForm = new LifeFormA();
//        var brain = brainFactory.create(lifeForm, SPEED, Emotion.neutral, MEMORIES);
//        assertTrue(brain instanceof DefaultBrain);
    }
}