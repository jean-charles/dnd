package com.gayasystem.games.dnd.lifeforms.brain.memories;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.PolarCoordinates;
import com.gayasystem.games.dnd.lifeforms.ThingA;
import com.gayasystem.games.dnd.lifeforms.ThingB;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.attracted;
import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.scared;

class EngramComputingTest {
    @Test
    void computeImageFound() {
        Collection<PersistedEngram> memories = List.of(new PersistedEngram(scared, new Image(ThingA.class)));
        var engramComputing = new EngramComputing();

        Collection<SpatialEngram> engrams = List.of(
                new SpatialEngram(
                        new Image(ThingA.class),
                        new PolarCoordinates(1.0, 0.0)));
        engramComputing.compute(Emotion.neutral, memories, engrams);
    }

    @Test
    void computeImageNotFound() {
        Collection<PersistedEngram> memories = List.of(new PersistedEngram(scared, new Image(ThingA.class)));
        var engramComputing = new EngramComputing();

        Collection<SpatialEngram> engrams = List.of(
                new SpatialEngram(
                        new Image(ThingB.class),
                        new PolarCoordinates(1.0, 0.0)));
        engramComputing.compute(Emotion.neutral, memories, engrams);
    }

    @Test
    void computeFearAttracted() {
        Collection<PersistedEngram> memories = List.of(
                new PersistedEngram(scared, new Image(ThingA.class)),
                new PersistedEngram(attracted, new Image(ThingB.class)));
        var engramComputing = new EngramComputing();

        Collection<SpatialEngram> engrams = List.of(
                new SpatialEngram(
                        new Image(ThingA.class),
                        new PolarCoordinates(1.0, 0.0)),
                new SpatialEngram(
                        new Image(ThingB.class),
                        new PolarCoordinates(1.0, 0.0)));
        engramComputing.compute(Emotion.neutral, memories, engrams);
    }

    @Test
    void computeFearThing() {
        Collection<PersistedEngram> memories = List.of(
                new PersistedEngram(scared, new Image(Thing.class)),
                new PersistedEngram(attracted, new Image(ThingB.class)));
        var engramComputing = new EngramComputing();

        Collection<SpatialEngram> engrams = List.of(
                new SpatialEngram(
                        new Image(ThingA.class),
                        new PolarCoordinates(1.0, 0.0)));
        engramComputing.compute(Emotion.neutral, memories, engrams);
    }
}
