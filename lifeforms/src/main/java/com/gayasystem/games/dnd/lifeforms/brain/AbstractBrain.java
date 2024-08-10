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
    private final double maxSpeed;
    private final Emotion defaultEmotion;
    private final Collection<PersistedEngram> longTermMemories = new ArrayList<>();
    private final Collection<SpatialEngram> shortTermMemories = new ArrayList<>();

    @Autowired
    private EngramComputing engramComputing;

    protected AbstractBrain(LifeForm body, double maxSpeed, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        if (body == null)
            throw new NullPointerException("body cannot be null");
        this.body = body;
        this.maxSpeed = maxSpeed;
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

    private Velocity move(SpatialEmotionalEngram mostImportantEngram) {
        var speedRate = computeSpeed(mostImportantEngram.emotion());
        var orientation = computeOrientation(mostImportantEngram);
        var engram = mostImportantEngram.engram();
        if (engram != null) {
            double rho = engram.origin().rho().doubleValue();
            var destination = new CircularCoordinate(rho, orientation);
            return new Velocity(maxSpeed * speedRate, destination);
        }
        return NO_VELOCITY;
    }

    private double computeSpeed(Emotion emotion) {
        switch (emotion) {
            case scared -> {
                return 1.0;
            }
            case attracted, hungry -> {
                return 0.75;
            }
            case neutral -> {
                return 0.5;
            }
        }
        return 0;
    }

    private Orientation computeOrientation(SpatialEmotionalEngram engram) {
        switch (engram.emotion()) {
            case scared -> {
                return engram.engram().origin().orientation().opposite();
            }
            case attracted -> {
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
            case move -> body.velocity(move(engram));
        }
    }

    /* UNIT TESTS ONLY */
    Collection<PersistedEngram> getLongTermMemories() {
        return longTermMemories;
    }

    Collection<SpatialEngram> getShortTermMemories() {
        return shortTermMemories;
    }
}
