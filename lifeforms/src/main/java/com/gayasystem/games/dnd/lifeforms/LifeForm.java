package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.NeuralNetworkInputsConverter;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.VelocityFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.body.organs.muscular.MuscularOrgan;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.SensitiveOrgan;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.SoundSpectrum;
import com.gayasystem.games.dnd.neuralnetwork.NeuralNetworkConfig;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public abstract class LifeForm extends Thing implements Moveable, Eater {
    public final Collection<SensitiveOrgan<?>> sensitiveOrgans;
    public final Collection<MuscularOrgan> muscularOrgans;
    private final Gender gender;
    private final double speed;
    private final double sightDistance;
    private final double nightSightDistance;
    private final SoundSpectrum soundSpectrum;
    private final double minSoundAmplitude;
    private final Emotion defaultEmotion;
    private final Map<Class<? extends Thing>, Emotion> longTermMemories;
    private final NeuralNetworkConfig neuralNetworkConfig;
    private final BrainFactory brainFactory;

    private Brain brain;
    private Velocity movement;
    private PolarCoordinates foodCoordinate;

    @Autowired
    private MeasurementConvertor convertor;

    @Autowired
    private VelocityFactory velocityFactory;

    /**
     * @param width              width in meter.
     * @param depth              depth in meter.
     * @param mass               mass in kilogram.
     * @param gender             female or male.
     * @param speed              in meter per second (m/s).
     * @param sightDistance      in meter.
     * @param nightSightDistance in meter.
     * @param soundSpectrum      TBD
     * @param minSoundAmplitude  TBD
     * @param longTermMemories   list of long term memories.
     * @param sensitiveOrgans    life form sensitive organs.
     * @param muscularOrgans     life form muscular organs.
     */
    public LifeForm(double width, double depth, double mass, Gender gender, double speed, double sightDistance, double nightSightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories, final Collection<SensitiveOrgan<?>> sensitiveOrgans, final Collection<MuscularOrgan> muscularOrgans, NeuralNetworkInputsConverter neuralNetworkInputsConverter, BrainFactory brainFactory) {
        super(width, depth, mass);
        this.gender = gender;
        this.speed = speed;
        this.sightDistance = sightDistance;
        this.nightSightDistance = nightSightDistance;
        this.soundSpectrum = soundSpectrum;
        this.minSoundAmplitude = minSoundAmplitude;
        this.defaultEmotion = defaultEmotion;
        this.longTermMemories = longTermMemories;
        this.sensitiveOrgans = Collections.unmodifiableCollection(sensitiveOrgans);
        this.muscularOrgans = Collections.unmodifiableCollection(muscularOrgans);
        this.brainFactory = brainFactory;

        int inputSize = neuralNetworkInputsConverter.inputSize(this.sensitiveOrgans);
        int hiddenSize = 20;
        int outputSize = 1;
        double learningRate = 0.01;
        neuralNetworkConfig = new NeuralNetworkConfig(inputSize, hiddenSize, outputSize, learningRate);
        brain = brainFactory.create(this, speed, defaultEmotion, longTermMemories, neuralNetworkConfig, this.sensitiveOrgans, this.muscularOrgans);
    }

    @Override
    public void run() {
        foodCoordinate = null;
        movement = null;

//        environment.show(this, convertor.miles2Inches(sightDistance));
//        environment.listen(this, minSoundAmplitude);
        brain.run();
//        if (foodCoordinate != null)
//            environment.eat(this);
//        if (movement != null)
//            environment.move(this, movement.azimuth(), movement);
    }

    @Override
    public void movement(Collection<MuscularOrgan> organs) {
        double[] outputs = new double[0];
        Velocity velocity = velocityFactory.create(outputs);
        movement = velocity;
    }

    @Override
    public void foodCoordinate(PolarCoordinates coordinate) {
        this.foodCoordinate = coordinate;
    }

    @Override
    public PolarCoordinates foodCoordinate() {
        return foodCoordinate;
    }

    @Override
    public void eat(Food food) {
        food.nourishment();
    }

    public Gender gender() {
        return gender;
    }

    public NeuralNetworkConfig neuralNetworkConfig() {
        return neuralNetworkConfig;
    }
}
