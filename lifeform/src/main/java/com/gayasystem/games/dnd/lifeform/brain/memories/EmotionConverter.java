package com.gayasystem.games.dnd.lifeform.brain.memories;

import com.gayasystem.games.dnd.common.Direction;
import com.gayasystem.games.dnd.common.Velocity;

public class EmotionConverter {
    public static double weight(Emotion emotion) {
        double weight = 0.0;
        switch (emotion) {
            case scared -> weight = 1.0;
            case attracted -> weight = 0.5;
        }
        return weight;
    }

    public static Velocity velocity(Emotion emotion) {
        Velocity velocity = new Velocity(0.0, new Direction(0.0, 0.0, 0.0));
        switch (emotion) {
            case scared -> velocity = new Velocity(1.0, new Direction(90.0, 0.0, 0.0));
            case attracted -> velocity = new Velocity(1.0, new Direction(0.0, 0.0, 0.0));
        }
        return velocity;

    }
}
