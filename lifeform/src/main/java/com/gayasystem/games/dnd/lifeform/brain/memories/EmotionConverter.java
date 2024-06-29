package com.gayasystem.games.dnd.lifeform.brain.memories;

import com.gayasystem.games.dnd.common.Direction;

public class EmotionConverter {
    public static double weight(Emotion emotion) {
        double weight = 0.0;
        switch (emotion) {
            case scared -> weight = 1.0;
            case attracted -> weight = 0.5;
        }
        return weight;
    }

    public static Direction direction(Emotion emotion) {
        Direction direction = new Direction(0.0, 0.0, 0.0);
        switch (emotion) {
            case scared -> direction = new Direction(90.0, 0.0, 0.0);
            case attracted -> direction = new Direction(0.0, 0.0, 0.0);
        }
        return direction;

    }
}
