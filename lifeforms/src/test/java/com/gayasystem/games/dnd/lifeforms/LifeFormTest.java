package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.hear.SoundSpectrum;
import com.gayasystem.games.dnd.lifeforms.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.List;

import static com.gayasystem.games.dnd.lifeforms.Gender.female;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@SpringBootTest(classes = {LifeFormTestConfig.class})
class LifeFormTest {
    static final double MASS = 1.2;
    static final Gender GENDER = female;
    static final double SPEED = 2.3;
    static final double SIGHT_DISTANCE = 100.0;
    static final double MAX_SPEED = 60.0;
    static final SoundSpectrum SOUND_SPECTRUM = new SoundSpectrum(10, 10000);
    static final double MIN_SOUND_AMPLITUDE = 20.0;
    static final Collection<Class<? extends Thing>> SCARED_BY = List.of();
    static final Collection<Class<? extends Thing>> ATTRACTED_BY = List.of();

    @MockBean
    BrainFactory brainFactory;

    @MockBean
    Brain brain;

    @Autowired
    LifeForm lifeForm;

    //    @BeforeEach
    void setUp() {
        when(brainFactory.create(lifeForm, SPEED, SCARED_BY, ATTRACTED_BY)).thenReturn(brain);
        lifeForm.run();
    }

    //    @Test
    void mass() {
        assertThat(brainFactory).isNotNull();
        assertThat(lifeForm).isNotNull();
        assertEquals(MASS, lifeForm.mass());
    }

    //    @Test
    void run() {
        lifeForm.run();
        verify(brainFactory, times(0)).create(lifeForm, SPEED, SCARED_BY, ATTRACTED_BY);
        verify(brain, times(2)).run();
    }

    //    @Test
    void see() {
        Orientation orientation = new Orientation(0);
        var thing = new ThingA();
        CircularCoordinate origin = new CircularCoordinate(10, 0);

        lifeForm.see(thing, origin, orientation);

        verify(brain).handle(any(SpatialEngram.class));
    }

    //    @Test
    void ear() {
        CircularCoordinate origin = new CircularCoordinate(10, 0);
        double amplitude = MIN_SOUND_AMPLITUDE * 2;
        var thing = new ThingA();

        lifeForm.ear(thing, SOUND_SPECTRUM, amplitude, origin);

        verify(brain).handle(any(SpatialEngram.class));
    }
}
