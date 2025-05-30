package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.SoundSpectrum;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static com.gayasystem.games.dnd.lifeforms.Gender.female;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {LifeFormA.class})
class LifeFormTest {
    static final double MASS = 1.2;
    static final Gender GENDER = female;
    static final double SPEED = 2.3;
    static final double SIGHT_DISTANCE = 100.0;
    static final double MAX_SPEED = 60.0;
    static final SoundSpectrum SOUND_SPECTRUM = new SoundSpectrum(10, 10000);
    static final double MIN_SOUND_AMPLITUDE = 20.0;
    static final Emotion DEFAULT_EMOTION = Emotion.neutral;
    static final Map<Class<? extends Thing>, Emotion> MEMORIES = Map.of();

    @MockBean
    BrainFactory brainFactory;

    @MockBean
    MeasurementConvertor convertor;

    @MockBean
    Brain brain;

    @Autowired
    LifeForm lifeForm;

    @BeforeEach
    void setUp() {
        when(brainFactory.create(eq(lifeForm), anyDouble(), any(), any(), any(), any(), any())).thenReturn(brain);
        lifeForm.run();
    }

    @Test
    void mass() {
        assertThat(brainFactory).isNotNull();
        assertThat(lifeForm).isNotNull();
        assertEquals(MASS, lifeForm.mass());
    }

    @Test
    void run() {
        lifeForm.run();
//        verify(brainFactory, times(0)).create(lifeForm, SPEED, Emotion.neutral, MEMORIES);
        verify(brain, times(2)).run();
    }

    @Test
    void see() {
        var thing = new ThingA();
        PolarCoordinates origin = PolarCoordinates.of(10, 0);

//        lifeForm.see(thing, origin, 0);

//        verify(brain).handle(any(SpatialEngram.class));
    }

    @Test
    void ear() {
        PolarCoordinates origin = PolarCoordinates.of(10, 0);
        double amplitude = MIN_SOUND_AMPLITUDE * 2;
        var thing = new ThingA();

//        lifeForm.ear(thing, SOUND_SPECTRUM, amplitude, origin);

//        verify(brain).handle(any(SpatialEngram.class));
    }
}
