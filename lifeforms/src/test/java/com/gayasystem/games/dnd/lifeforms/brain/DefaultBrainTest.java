package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DefaultBrainTest {
    @Test
    public void memories() {
        new DefaultBrain(null, 0, List.of(Thing.class), List.of());
    }
}
