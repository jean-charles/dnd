package com.gayasystem.games.dnd.lifeforms.brain.memories;

import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.Action.*;
import static com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion.neutral;

@Service
public class EngramComputing {
    private PersistedEngram search(Collection<PersistedEngram> memories, SpatialEngram engram) {
//        Class<? extends Thing> thing = engram.engram().thingClass();
        PersistedEngram similarMemory = null;

        for (var memory : memories) {
//            var memoryThing = memory.engram().thingClass();
//            if (memoryThing.isAssignableFrom(thing)) {
//                if (memoryThing.equals(thing))
//                    return memory;
            similarMemory = memory;
//            }
        }
        return similarMemory;
    }

    private SpatialEmotionalEngram findMostImportantEngram(Collection<SpatialEmotionalEngram> spatialEmotionalEngrams) {
        var mostImportantEngram = new SpatialEmotionalEngram(null, neutral);
        double closestDistance = Double.MAX_VALUE;
        Emotion mostImportantEmotion = neutral;
        for (var spatialEmotionalEngram : spatialEmotionalEngrams) {
            var distance = spatialEmotionalEngram.engram().origin().getRadius();
            if (mostImportantEmotion(spatialEmotionalEngram.emotion(), mostImportantEmotion)) {
//                if (distance < closestDistance) {
                mostImportantEngram = spatialEmotionalEngram;
//                    closestDistance = distance;
                mostImportantEmotion = spatialEmotionalEngram.emotion();
//                }
            }
        }
        return mostImportantEngram;
    }

    private boolean mostImportantEmotion(Emotion e1, Emotion e2) {
        return e1.ordinal() <= e2.ordinal();
    }

    private Action next(SpatialEmotionalEngram mostImportantEngram) {
        return switch (mostImportantEngram.emotion()) {
            case attracted, scared -> move;
            case hungry -> {
                var spatialEngram = mostImportantEngram.engram();
                var distance = spatialEngram.origin().getRadius();
                if (distance <= 0)
                    yield eat;
                yield move;
            }
            default -> doNothing;
        };
    }

    public NextAction compute(Emotion defaultEmotion, Collection<PersistedEngram> memories, Collection<SpatialEngram> engrams) {
        Collection<SpatialEmotionalEngram> emotionalEngrams = new ArrayList<>();

        for (var engram : engrams) {
            var foundMemory = search(memories, engram);
            if (foundMemory != null) {
                var emotion = foundMemory.emotion();
                emotionalEngrams.add(new SpatialEmotionalEngram(engram, emotion));
            } else {
                emotionalEngrams.add(new SpatialEmotionalEngram(engram, defaultEmotion));
            }
        }

        var mostImportantEngram = findMostImportantEngram(emotionalEngrams);
        return switch (next(mostImportantEngram)) {
            case doNothing -> new NextAction(doNothing);
            case eat -> new NextAction(eat, mostImportantEngram);
            case move -> new NextAction(move, mostImportantEngram);
        };
    }
}
