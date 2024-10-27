package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.*;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
public abstract class AbstractBrain implements Brain {
    private static final Velocity NO_VELOCITY = new Velocity(0, new CircularCoordinate(0, 0));

    private final LifeForm body;
    private final double maxSpeedPerSecond;
    private final Emotion defaultEmotion;
    private final Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private final Collection<SpatialEngram> shortTermMemories = new ArrayList<>();

    @Autowired
    private EngramComputing engramComputing;

    /**
     * @param body              body that contain the brain
     * @param maxSpeedPerSecond max speed in feet per second
     * @param defaultEmotion    default emotion for unknown things
     * @param longTermMemories  predetermined memories
     */
    protected AbstractBrain(LifeForm body, double maxSpeedPerSecond, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        if (body == null)
            throw new NullPointerException("body cannot be null");
        this.body = body;
        this.maxSpeedPerSecond = maxSpeedPerSecond;
        this.defaultEmotion = defaultEmotion;
        rememberLongTermMemories(longTermMemories);
    }

    private void rememberLongTermMemories(Map<Class<? extends Thing>, Emotion> longTermMemories) {
        for (var thingClass : longTermMemories.keySet()) {
            var emotion = longTermMemories.get(thingClass);
            Engram engram = new Image(thingClass);
            var persistedEngram = new PersistedEngram(emotion, engram);
            this.longTermMemories.add(persistedEngram);
        }
    }

    private Velocity computeVelocity(SpatialEmotionalEngram mostImportantEngram) {
        var speed = computeSpeed(mostImportantEngram.emotion());
        var orientation = computeOrientation(mostImportantEngram);
        var engram = mostImportantEngram.engram();
        if (engram != null) {
            double rho = engram.origin().rho().doubleValue();
            var destination = new CircularCoordinate(rho, orientation);
            return new Velocity(speed, destination);
        }
        return NO_VELOCITY;
    }

    private double computeSpeed(Emotion emotion) {
        double speedRate = switch (emotion) {
            case scared -> 1.0;
            case hungry, attracted -> 0.75;
            case neutral -> 0.5;
            default -> 0;
        };
        return speedRate * maxSpeedPerSecond;
    }

    private Orientation computeOrientation(SpatialEmotionalEngram engram) {
        switch (engram.emotion()) {
            case scared -> {
                return engram.engram().origin().orientation().opposite();
            }
            case hungry, attracted -> {
                return engram.engram().origin().orientation();
            }
        }
        return new Orientation(0);
    }

    @Override
    public void handle(SpatialEngram engram) {
        shortTermMemories.add(engram);
    }

    @Override
    public void run() {
        var nextAction = engramComputing.compute(defaultEmotion, longTermMemories, shortTermMemories);
        var engram = nextAction.spatialEmotionalEngram();
        switch (nextAction.action()) {
            case doNothing -> {
            }
            case eat -> body.foodCoordinate(engram.origin());
            case move -> {
                Velocity velocity = computeVelocity(engram);
                body.velocity(velocity);
                body.rotation(velocity.destination().orientation());
            }
        }
        shortTermMemories.clear();
    }

    /* UNIT TESTS ONLY */
    Collection<PersistedEngram> getLongTermMemories() {
        return longTermMemories;
    }

    Collection<SpatialEngram> getShortTermMemories() {
        return shortTermMemories;
    }
}
