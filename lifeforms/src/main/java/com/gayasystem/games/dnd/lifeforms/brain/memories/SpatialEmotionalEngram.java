package com.gayasystem.games.dnd.lifeforms.brain.memories;

import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;

public record SpatialEmotionalEngram(SpatialEngram engram, Emotion emotion) {
    public PolarCoordinates origin() {
        return engram.origin();
    }
}
