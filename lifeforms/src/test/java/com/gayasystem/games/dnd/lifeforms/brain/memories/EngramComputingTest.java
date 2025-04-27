package com.gayasystem.games.dnd.lifeforms.brain.memories;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.EngramComputing;
import org.junit.jupiter.api.Test;

class EngramComputingTest {
    @Test
    void computeImageFound() {
//        Collection<PersistedEngram> memories = List.of(new PersistedEngram(scared, new Image(ThingA.class)));
        var engramComputing = new EngramComputing();

//        Collection<SpatialEngram> engrams = List.of(
//                new SpatialEngram(
//                        new Image(ThingA.class),
//                        PolarCoordinates.of(1.0, 0.0)));
//        engramComputing.compute(Emotion.neutral, memories, engrams);
    }

    @Test
    void computeImageNotFound() {
//        Collection<PersistedEngram> memories = List.of(new PersistedEngram(scared, new Image(ThingA.class)));
        var engramComputing = new EngramComputing();

//        Collection<SpatialEngram> engrams = List.of(
//                new SpatialEngram(
//                        new Image(ThingB.class),
//                        PolarCoordinates.of(1.0, 0.0)));
//        engramComputing.compute(Emotion.neutral, memories, engrams);
    }

    @Test
    void computeFearAttracted() {
//        Collection<PersistedEngram> memories = List.of(
//                new PersistedEngram(scared, new Image(ThingA.class)),
//                new PersistedEngram(attracted, new Image(ThingB.class)));
        var engramComputing = new EngramComputing();

//        Collection<SpatialEngram> engrams = List.of(
//                new SpatialEngram(
//                        new Image(ThingA.class),
//                        PolarCoordinates.of(1.0, 0.0)),
//                new SpatialEngram(
//                        new Image(ThingB.class),
//                        PolarCoordinates.of(1.0, 0.0)));
//        engramComputing.compute(Emotion.neutral, memories, engrams);
    }

    @Test
    void computeFearThing() {
//        Collection<PersistedEngram> memories = List.of(
//                new PersistedEngram(scared, new Image(Thing.class)),
//                new PersistedEngram(attracted, new Image(ThingB.class)));
        var engramComputing = new EngramComputing();

//        Collection<SpatialEngram> engrams = List.of(
//                new SpatialEngram(
//                        new Image(ThingA.class),
//                        PolarCoordinates.of(1.0, 0.0)));
//        engramComputing.compute(Emotion.neutral, memories, engrams);
    }
}
