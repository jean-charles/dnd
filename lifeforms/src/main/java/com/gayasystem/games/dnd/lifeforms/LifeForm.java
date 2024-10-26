package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.hear.SoundSpectrum;
import com.gayasystem.games.dnd.common.sight.Sighted;
import com.gayasystem.games.dnd.lifeforms.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;
import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.Sound;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class LifeForm extends Thing implements Sighted, Hearing, Eater {
    public static final double CATCHING_DISTANCE = 0.5;

    private final Gender gender;
    private final double maxSpeedPerSecond;
    private final double sightDistance;
    private final double nightSightDistance;
    private final SoundSpectrum soundSpectrum;
    private final double minSoundAmplitude;
    private final Emotion defaultEmotion;
    private final Map<Class<? extends Thing>, Emotion> longTermMemories;

    private Brain brain;
    private CircularCoordinate foodCoordinate;

    @Autowired
    private LifeEnvironment environment;

    @Autowired
    private BrainFactory brainFactory;

    @Autowired
    private MeasurementConvertor convertor;

    /**
     * @param width  width in feet.
     * @param depth  depth in feet.
     * @param mass  mass in pounds.
     * @param gender             female or male.
     * @param speed              in feet.
     * @param sightDistance      in miles.
     * @param nightSightDistance in feet.
     * @param soundSpectrum
     * @param minSoundAmplitude
     * @param longTermMemories   list of long term memories.
     */
    public LifeForm(double width, double depth, double mass, Gender gender, double speed, double sightDistance, double nightSightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        super(width, depth, mass);
        this.gender = gender;
        this.maxSpeedPerSecond = speed / 6;
        this.sightDistance = sightDistance;
        this.nightSightDistance = nightSightDistance;
        this.soundSpectrum = soundSpectrum;
        this.minSoundAmplitude = minSoundAmplitude;
        this.defaultEmotion = defaultEmotion;
        this.longTermMemories = longTermMemories;
    }

    @Override
    public void run() {
        if (brain == null)
            brain = brainFactory.create(this, maxSpeedPerSecond, defaultEmotion, longTermMemories);

        foodCoordinate = null;
        velocity = null;

        environment.show(this, convertor.miles2Inches(sightDistance));
        environment.listen(this, minSoundAmplitude);
        brain.run();
        if (foodCoordinate != null)
            environment.eat(this);
        if (velocity() != null)
            environment.move(this);
    }

    @Override
    public void see(Thing thing, CircularCoordinate origin, Orientation orientation) {
        Image image = new Image(thing.getClass());
        brain.handle(new SpatialEngram(image, origin));
    }

    @Override
    public void ear(Thing thing, SoundSpectrum spectrum, double amplitude, CircularCoordinate origin) {
        var sound = new Sound(thing.getClass(), spectrum, amplitude);
        if (sound.spectrum().equals(soundSpectrum) && sound.amplitude() >= minSoundAmplitude) {
            brain.handle(new SpatialEngram(sound, origin));
        }
    }

    @Override
    public void foodCoordinate(CircularCoordinate coordinate) {
        this.foodCoordinate = coordinate;
    }

    @Override
    public CircularCoordinate foodCoordinate() {
        return foodCoordinate;
    }

    @Override
    public void eat(Food food) {
        food.nourishment();
    }

    public Gender gender() {
        return gender;
    }
}
