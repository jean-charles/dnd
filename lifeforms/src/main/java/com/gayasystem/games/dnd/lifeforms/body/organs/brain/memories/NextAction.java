package com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories;

public record NextAction(Action action, SpatialEmotionalEngram spatialEmotionalEngram) {
    public NextAction(Action action) {
        this(action, null);
    }
}
