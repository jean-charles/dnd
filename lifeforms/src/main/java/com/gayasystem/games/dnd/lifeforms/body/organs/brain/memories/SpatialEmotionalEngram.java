package com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

public record SpatialEmotionalEngram(SpatialEngram engram, Emotion emotion) {
    public PolarCoordinates origin() {
        return engram.origin();
    }
}
