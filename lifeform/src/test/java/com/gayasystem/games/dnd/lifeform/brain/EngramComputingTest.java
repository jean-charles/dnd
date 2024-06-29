package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.lifeform.brain.images.Image;
import com.gayasystem.games.dnd.lifeform.brain.memories.Engram;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EngramComputingTest {
    private EngramComputing engramComputing = new EngramComputing();

    @Test
    void computeImage() {
        Collection<Engram> engrams = new ArrayList<>();
        Thing thing = mock(Thing.class);
        engrams.add(new Image(thing));

        Moveable moveable = mock(Moveable.class);
        engramComputing.compute(engrams, moveable);

        verify(moveable).setVelocity(any(Velocity.class));
    }
}