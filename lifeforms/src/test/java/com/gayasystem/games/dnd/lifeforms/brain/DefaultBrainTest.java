package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.DefaultBrain;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {DefaultBrain.class})
public class DefaultBrainTest {
    private static final Map<Class<? extends Thing>, Emotion> LONG_TERM_MEMORIES = Map.of(
//            ThingA.class, attracted,
//            ThingB.class, scared
    );

    @MockBean
    BrainFactory brainFactory;

    @MockBean
    EngramComputing engramComputing;

    @MockBean
    LifeForm lifeForm;

    @Autowired
    ApplicationContext ctx;

    DefaultBrain brain;

    @BeforeEach
    public void setUp() {
        assertNotNull(engramComputing);
        assertNotNull(lifeForm);
//        brain = ctx.getBean(DefaultBrain.class, lifeForm, 10, neutral, LONG_TERM_MEMORIES);
//        assertNotNull(brain);
    }

    @Test
    public void noMemories() {
//        var brain = ctx.getBean(DefaultBrain.class, lifeForm, 10, neutral, Map.of());
//        assertEquals(0, brain.getLongTermMemories().size());
//        assertEquals(0, brain.getShortTermMemories().size());
    }

    @Test
    public void longTermMemoriesMemories() {
//        var engrams = brain.getLongTermMemories();
//        assertEquals(2, engrams.size());
//        assertEquals(0, brain.getShortTermMemories().size());
//        for (var engram : engrams) {
//            var thing = engram.engram().thingClass();
//            var emotion = engram.emotion();
//            if (thing == ThingA.class)
//                assertEquals(Emotion.attracted, emotion);
//            else if (thing == ThingB.class)
//                assertEquals(Emotion.scared, emotion);
//            else
//                fail();
//        }
    }

//    @Test
//    public void shortTermMemoriesMemoriesAttracted() {
//        when(engramComputing.compute(any(), anyCollection(), anyCollection())).thenReturn(new NextAction(Action.doNothing));
//        PolarCoordinates coordinates = PolarCoordinates.of(10, 0);
//        brain.handle(new SpatialEngram(new Image(ThingA.class), coordinates));
//        brain.run();
//    }
}
