package com.gayasystem.games.dnd.lifeform.brain.memories;

import com.gayasystem.games.dnd.common.SpericalCoordinate;

public class EmotionConverter {
    public static double weight(Emotion emotion) {
        double weight = 0.0;
        switch (emotion) {
            case scared -> weight = 1.0;
            case attracted -> weight = 0.5;
        }
        return weight;
    }

    public static SpericalCoordinate direction(SpericalCoordinate origin, Emotion emotion) {
        SpericalCoordinate spericalCoordinate = new SpericalCoordinate(0.0, 0.0, 0.0);
        switch (emotion) {
            case scared -> spericalCoordinate = origin.add(new SpericalCoordinate(180.0, 0.0, 0.0));
            case attracted -> spericalCoordinate = origin.add(new SpericalCoordinate(0.0, 0.0, 0.0));
        }
        return spericalCoordinate;
    }
}
