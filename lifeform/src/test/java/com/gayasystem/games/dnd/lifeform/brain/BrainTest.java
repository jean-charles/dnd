package com.gayasystem.games.dnd.lifeform.brain;

import com.gayasystem.games.dnd.common.Thing;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BrainTest {
    @Test
    public void memories() {
        new Brain(null, List.of(Thing.class), List.of());
    }
}
