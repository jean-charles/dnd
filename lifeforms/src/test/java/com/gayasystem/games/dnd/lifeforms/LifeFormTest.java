package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Orientation;
import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.Sound;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.SoundSpectrum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {LifeFormTestConfig.class})
class LifeFormTest {
    static final double MASS = 1.2;
    static final double SPEED = 2.3;
    static final double SIGHT_DISTANCE = 100.0;
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

    @BeforeEach
    void setUp() {
        when(brainFactory.create(lifeForm, SPEED, SCARED_BY, ATTRACTED_BY)).thenReturn(brain);
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
        verify(brainFactory, times(0)).create(lifeForm, SPEED, SCARED_BY, ATTRACTED_BY);
        verify(brain, times(2)).run();
    }

    @Test
    void see() {
        Orientation orientation = new Orientation(0, 0);
        var image = new Image(ThingA.class, orientation);
        SphericalCoordinate origin = new SphericalCoordinate(10, 0, 0);

        lifeForm.see(image, origin);

        verify(brain).handle(new SpatialEngram(image, origin));
    }

    @Test
    void ear() {
        SphericalCoordinate origin = new SphericalCoordinate(10, 0, 0);
        double amplitude = MIN_SOUND_AMPLITUDE * 2;
        var sound = new Sound(ThingA.class, SOUND_SPECTRUM, amplitude);

        lifeForm.ear(sound, origin);

        verify(brain).handle(new SpatialEngram(sound, origin));
    }

    @Test
    void sightDistance() {
        assertEquals(SIGHT_DISTANCE, lifeForm.sightDistance());
    }
}
