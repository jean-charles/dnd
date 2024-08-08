package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.ThingA;
import com.gayasystem.games.dnd.lifeforms.ThingB;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.PersistedEngram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

//@SpringBootTest(classes = {LifeFormTestConfig.class, LifeFormA.class, DefaultBrain.class})
public class DefaultBrainTest {
    @MockBean
    EngramComputing engramComputing;

    @Autowired
    LifeForm lifeForm;

    DefaultBrain brain;

    //    @BeforeEach
    public void setUp(ApplicationContext ctx) {
        assertNotNull(engramComputing);
        assertNotNull(lifeForm);
        brain = ctx.getBean(DefaultBrain.class, lifeForm, 10, List.of(ThingA.class), List.of(ThingB.class));
    }

    //    @Test
    public void noMemories() {
        var brain = new DefaultBrain(lifeForm, 10, List.of(), List.of());
        assertNotNull(brain);
        assertEquals(0, brain.getLongTermMemories().size());
        assertEquals(0, brain.getShortTermMemories().size());
    }

    //    @Test
    public void longTermMemoriesMemories() {
        var engrams = brain.getLongTermMemories();
        assertEquals(2, engrams.size());
        assertEquals(0, brain.getShortTermMemories().size());
        for (var engram : engrams) {
            var thing = engram.engram().thingClass();
            var emotion = engram.emotion();
            if (thing == ThingA.class)
                assertEquals(Emotion.attracted, emotion);
            else if (thing == ThingB.class)
                assertEquals(Emotion.scared, emotion);
            else
                fail();
        }
    }

    //    @Test
    public void shortTermMemoriesMemoriesAttracted() {
        CircularCoordinate coordinates = new CircularCoordinate(10, 0);
        brain.handle(new SpatialEngram(new Image(ThingA.class), coordinates));
        brain.run();
        verify(engramComputing).compute(eq(lifeForm), eq(10), ArgumentMatchers.<Collection<PersistedEngram>>any(), ArgumentMatchers.<Collection<SpatialEngram>>any());
    }
}
