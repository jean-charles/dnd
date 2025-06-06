package com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions;

import com.gayasystem.games.dnd.common.coordinates.Orientation;

public class EmotionConverter {
    public static double weight(Emotion emotion) {
        double weight = 0.0;
        switch (emotion) {
            case scared -> weight = 1.0;
            case hungry, attracted -> weight = 0.5;
        }
        return weight;
    }

    public static Orientation orientation(Orientation origin, Emotion emotion) {
        Orientation orientation = new Orientation(0.0);
        switch (emotion) {
            case scared -> orientation = origin.opposite();
            case hungry, attracted -> orientation = origin;
        }
        return orientation;
    }
}
