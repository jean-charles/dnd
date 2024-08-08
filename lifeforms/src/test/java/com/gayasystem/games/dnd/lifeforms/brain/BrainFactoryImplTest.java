package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.LifeFormA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest(classes = {LifeFormTestConfig.class})
class BrainFactoryImplTest {
    static final double SPEED = 2.3;
    static final Collection<Class<? extends Thing>> SCARED_BY = List.of();
    static final Collection<Class<? extends Thing>> ATTRACTED_BY = List.of();

    @Autowired
    BrainFactory brainFactory;

    //    @Test
    void create() {
        LifeForm lifeForm = new LifeFormA();
        var brain = brainFactory.create(lifeForm, SPEED, ATTRACTED_BY, SCARED_BY);
        assertTrue(brain instanceof DefaultBrain);
    }
}