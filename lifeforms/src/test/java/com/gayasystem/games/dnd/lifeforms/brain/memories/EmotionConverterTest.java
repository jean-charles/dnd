package com.gayasystem.games.dnd.lifeforms.brain.memories;

import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.EmotionConverter;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmotionConverterTest {
    @Test
    void weight() {
        assertEquals(1.0, EmotionConverter.weight(Emotion.scared));
        assertEquals(0.5, EmotionConverter.weight(Emotion.attracted));
    }

    @Test
    void directionScared() {
        Orientation o;
        o = EmotionConverter.orientation(new Orientation(0), Emotion.scared);
        assertEquals(new Orientation(PI), o);

        o = EmotionConverter.orientation(new Orientation(PI / 2), Emotion.scared);
        assertEquals(new Orientation(3 * PI / 2).phi().doubleValue(), o.phi().doubleValue(), 0.00000000000001);

        o = EmotionConverter.orientation(new Orientation(PI / 4), Emotion.scared);
        assertEquals(new Orientation(5 * PI / 4).phi().doubleValue(), o.phi().doubleValue(), 0.00000000000001);
    }

    @Test
    void directionAttracted() {
        Orientation d;
        d = EmotionConverter.orientation(new Orientation(0), Emotion.attracted);
        assertEquals(new Orientation(0), d);

        d = EmotionConverter.orientation(new Orientation(0), Emotion.attracted);
        assertEquals(new Orientation(0), d);

        d = EmotionConverter.orientation(new Orientation(PI / 4), Emotion.attracted);
        assertEquals(new Orientation(PI / 4), d);
    }
}