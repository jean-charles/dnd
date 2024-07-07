package com.gayasystem.games.dnd.lifeform.brain.memories.emotions;

import com.gayasystem.games.dnd.common.SphericalCoordinate;

public class EmotionConverter {
    public static double weight(Emotion emotion) {
        double weight = 0.0;
        switch (emotion) {
            case scared -> weight = 1.0;
            case attracted -> weight = 0.5;
        }
        return weight;
    }

    public static SphericalCoordinate direction(SphericalCoordinate origin, Emotion emotion) {
        SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(0.0, 0.0, 0.0);
        switch (emotion) {
            case scared -> sphericalCoordinate = origin.opposite();
            case attracted -> sphericalCoordinate = origin;
        }
        return sphericalCoordinate;
    }
}
