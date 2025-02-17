package com.gayasystem.games.dnd.ecosystem.beasts;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.lifeforms.LifeEnvironment;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactoryImpl;
import com.gayasystem.games.dnd.lifeforms.brain.DefaultBrain;
import com.gayasystem.games.dnd.lifeforms.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.NextAction;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.Action.doNothing;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ApplicationContext.class, BrainFactoryImpl.class, DefaultBrain.class, Almiraj.class})
class AlmirajTest {
    @Autowired
    private ApplicationContext ctx;

    @MockBean
    private LifeEnvironment env;

    @MockBean
    private MeasurementConvertor mc;

    @MockBean
    private EngramComputing ec;

    @BeforeEach
    void setUp() throws Exception {
        when(ec.compute(any(Emotion.class), anyCollection(), anyCollection())).thenReturn(new NextAction(doNothing));
    }

    @Test
    void newAlmiraj() throws Exception {
        var a = ctx.getBean(Almiraj.class);
        assertNotNull(a);
        a.run();
    }
}
