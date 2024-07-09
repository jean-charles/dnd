package com.gayasystem.games.dnd.lifeforms.brain.memories;

import com.gayasystem.games.dnd.common.Orientation;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.EmotionConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmotionConverterTest {

    @Test
    void weight() {
        assertEquals(1.0, EmotionConverter.weight(Emotion.scared));
        assertEquals(0.5, EmotionConverter.weight(Emotion.attracted));
    }

    @Test
    void directionScared() {
        Orientation d;
        d = EmotionConverter.orientation(new Orientation(0, 0), Emotion.scared);
//        assertEquals(new Direction(-180, 0, 0), d);

        d = EmotionConverter.orientation(new Orientation(0, 0), Emotion.scared);
//        assertEquals(new Direction(-135, 0, 0), d);

        d = EmotionConverter.orientation(new Orientation(45, 0), Emotion.scared);
//        assertEquals(new Direction(-135, -45, 0), d);
    }

    @Test
    void directionAttracted() {
        Orientation d;
        d = EmotionConverter.orientation(new Orientation(0, 0), Emotion.attracted);
        assertEquals(new Orientation(0, 0), d);

        d = EmotionConverter.orientation(new Orientation(0, 0), Emotion.attracted);
        assertEquals(new Orientation(0, 0), d);

        d = EmotionConverter.orientation(new Orientation(45, 0), Emotion.attracted);
        assertEquals(new Orientation(45, 0), d);
    }
}