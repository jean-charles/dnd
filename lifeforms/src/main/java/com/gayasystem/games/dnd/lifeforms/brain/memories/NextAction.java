package com.gayasystem.games.dnd.lifeforms.brain.memories;

public record NextAction(Action action, SpatialEmotionalEngram spatialEmotionalEngram) {
    public NextAction(Action action) {
        this(action, null);
    }
}
