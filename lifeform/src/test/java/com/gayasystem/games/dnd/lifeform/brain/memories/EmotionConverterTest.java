package com.gayasystem.games.dnd.lifeform.brain.memories;

import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.lifeform.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeform.brain.memories.emotions.EmotionConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmotionConverterTest {

    @Test
    void weight() {
        assertEquals(1.0, EmotionConverter.weight(Emotion.scared));
        assertEquals(0.5, EmotionConverter.weight(Emotion.attracted));
    }

    @Test
    void directionScared() {
        SphericalCoordinate d;
        d = EmotionConverter.direction(new SphericalCoordinate(0, 0, 0), Emotion.scared);
//        assertEquals(new Direction(-180, 0, 0), d);

        d = EmotionConverter.direction(new SphericalCoordinate(45, 0, 0), Emotion.scared);
//        assertEquals(new Direction(-135, 0, 0), d);

        d = EmotionConverter.direction(new SphericalCoordinate(45, 45, 0), Emotion.scared);
//        assertEquals(new Direction(-135, -45, 0), d);
    }

    @Test
    void directionAttracted() {
        SphericalCoordinate d;
        d = EmotionConverter.direction(new SphericalCoordinate(0, 0, 0), Emotion.attracted);
        assertEquals(new SphericalCoordinate(0, 0, 0), d);

        d = EmotionConverter.direction(new SphericalCoordinate(45, 0, 0), Emotion.attracted);
        assertEquals(new SphericalCoordinate(45, 0, 0), d);

        d = EmotionConverter.direction(new SphericalCoordinate(45, 45, 0), Emotion.attracted);
        assertEquals(new SphericalCoordinate(45, 45, 0), d);
    }
}