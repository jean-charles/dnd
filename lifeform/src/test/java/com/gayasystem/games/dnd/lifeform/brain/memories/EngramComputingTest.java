package com.gayasystem.games.dnd.lifeform.brain.memories;

import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static com.gayasystem.games.dnd.lifeform.brain.memories.emotions.Emotion.attracted;
import static com.gayasystem.games.dnd.lifeform.brain.memories.emotions.Emotion.scared;
import static java.lang.Math.PI;
import static org.mockito.Mockito.*;

class EngramComputingTest {
    @Test
    void computeImageFound() {
        Collection<PersistedEngram> memories = List.of(new PersistedEngram(scared, new Image(ThingA.class)));
        Moveable moveable = mock(Moveable.class);
        var engramComputing = new EngramComputing(memories, moveable);

        Collection<SpatialEngram> engrams = List.of(
                new SpatialEngram(
                        new Image(ThingA.class),
                        new SphericalCoordinate(1.0, 0.0, 0.0)));
        engramComputing.compute(engrams);

        verify(moveable, times(1)).setVelocity(new SphericalCoordinate(1.0, PI, PI), 0.0);
    }

    @Test
    void computeImageNotFound() {
        Collection<PersistedEngram> memories = List.of(new PersistedEngram(scared, new Image(ThingA.class)));
        Moveable moveable = mock(Moveable.class);
        var engramComputing = new EngramComputing(memories, moveable);

        Collection<SpatialEngram> engrams = List.of(
                new SpatialEngram(
                        new Image(ThingB.class),
                        new SphericalCoordinate(1.0, 0.0, 0.0)));
        engramComputing.compute(engrams);

        verify(moveable, times(1)).setVelocity(new SphericalCoordinate(0.0, 0.0, 0.0), 0.0);
    }

    @Test
    void computeFearAttracted() {
        Collection<PersistedEngram> memories = List.of(
                new PersistedEngram(scared, new Image(ThingA.class)),
                new PersistedEngram(attracted, new Image(ThingB.class)));
        Moveable moveable = mock(Moveable.class);
        var engramComputing = new EngramComputing(memories, moveable);

        Collection<SpatialEngram> engrams = List.of(
                new SpatialEngram(
                        new Image(ThingA.class),
                        new SphericalCoordinate(1.0, 0.0, 0.0)),
                new SpatialEngram(
                        new Image(ThingB.class),
                        new SphericalCoordinate(1.0, 0.0, 0.0)));
        engramComputing.compute(engrams);

        verify(moveable, times(1)).setVelocity(new SphericalCoordinate(1.0, PI, PI), 0.0);
    }

    @Test
    void computeFearThing() {
        Collection<PersistedEngram> memories = List.of(
                new PersistedEngram(scared, new Image(Thing.class)),
                new PersistedEngram(attracted, new Image(ThingB.class)));
        Moveable moveable = mock(Moveable.class);
        var engramComputing = new EngramComputing(memories, moveable);

        Collection<SpatialEngram> engrams = List.of(
                new SpatialEngram(
                        new Image(ThingA.class),
                        new SphericalCoordinate(1.0, 0.0, 0.0)));
        engramComputing.compute(engrams);

        verify(moveable, times(1)).setVelocity(new SphericalCoordinate(1.0, PI, PI), 0.0);
    }
}

class ThingA extends Thing {
    protected ThingA() {
        super(1);
    }

    @Override
    public double mass() {
        return 0;
    }

    @Override
    public void run() {

    }
}

class ThingB extends Thing {
    protected ThingB() {
        super(1);
    }

    @Override
    public double mass() {
        return 0;
    }

    @Override
    public void run() {

    }
}