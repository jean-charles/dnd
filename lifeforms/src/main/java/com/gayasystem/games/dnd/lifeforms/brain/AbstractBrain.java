package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.*;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static com.gayasystem.games.dnd.common.Velocity.NO_VELOCITY;

@Component
public abstract class AbstractBrain implements Brain {
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
            double rho = engram.origin().getRadius();
            return new Velocity(speed, rho, orientation);
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

    private double computeOrientation(SpatialEmotionalEngram engram) {
        switch (engram.emotion()) {
            case scared -> {
                var origin = engram.engram().origin();
                var point = Point1S.from(origin);
                return point.antipodal().getAzimuth();
            }
            case hungry, attracted -> {
                return engram.engram().origin().getAzimuth();
            }
        }
        return 0;
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
                body.movement(velocity);
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
