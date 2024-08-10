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
    private final Gender gender;
    private final double maxSpeed;
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
     * @param mass               in pounds.
     * @param gender
     * @param maxSpeed in feet per hour.
     * @param sightDistance      in miles.
     * @param nightSightDistance in foot.
     * @param soundSpectrum
     * @param minSoundAmplitude
     * @param longTermMemories
     */
    public LifeForm(double mass, Gender gender, double maxSpeed, double sightDistance, double nightSightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        super(mass);
        this.gender = gender;
        this.maxSpeed = maxSpeed;
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
            brain = brainFactory.create(this, maxSpeed, defaultEmotion, longTermMemories);
        environment.show(this, convertor.miles2Inches(sightDistance));
        environment.listen(this, minSoundAmplitude);
        brain.run();
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
    }
}
