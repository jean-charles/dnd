package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.common.Direction;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeform.brain.memories.PersistedEngram;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static com.gayasystem.games.dnd.lifeform.brain.memories.Emotion.attracted;
import static com.gayasystem.games.dnd.lifeform.brain.memories.Emotion.scared;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EngramComputingTest {
    @Test
    void computeImageFound() {
        Collection<PersistedEngram> memories = List.of(new PersistedEngram(scared, new Image(ThingA.class)));
        Moveable moveable = mock(Moveable.class);
        var engramComputing = new EngramComputing(memories, moveable);

        Collection<Engram> engrams = List.of(new Image(ThingA.class));
        engramComputing.compute(engrams);

        verify(moveable).setDirection(any(Direction.class));
    }

    @Test
    void computeImageNotFound() {
        Collection<PersistedEngram> memories = List.of(new PersistedEngram(scared, new Image(ThingA.class)));
        Moveable moveable = mock(Moveable.class);
        var engramComputing = new EngramComputing(memories, moveable);

        Collection<Engram> engrams = List.of(new Image(ThingB.class));
        engramComputing.compute(engrams);

        verify(moveable, never()).setDirection(any(Direction.class));
    }

    @Test
    void computeFearAttracted() {
        Collection<PersistedEngram> memories = List.of(
                new PersistedEngram(scared, new Image(ThingA.class)),
                new PersistedEngram(attracted, new Image(ThingB.class)));
        Moveable moveable = mock(Moveable.class);
        var engramComputing = new EngramComputing(memories, moveable);

        Collection<Engram> engrams = List.of(new Image(ThingA.class));
        engramComputing.compute(engrams);

        verify(moveable).setDirection(new Direction(90.0, 0.0, 0.0));

        engrams = List.of(new Image(ThingB.class));
        engramComputing.compute(engrams);

        verify(moveable).setDirection(new Direction(0.0, 0.0, 0.0));
    }
}

class ThingA implements Thing {
    @Override
    public double mass() {
        return 0;
    }
}

class ThingB implements Thing {
    @Override
    public double mass() {
        return 0;
    }
}