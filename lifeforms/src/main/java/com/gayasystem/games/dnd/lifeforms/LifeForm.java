package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.lifeforms.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.brain.NeuralNetworkInputsConverter;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.organs.Organ;
import com.gayasystem.games.dnd.lifeforms.sensitive.stimuli.SoundSpectrum;
import com.gayasystem.games.dnd.neuralnetwork.NeuralNetworkConfig;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public abstract class LifeForm extends Thing implements Moveable, Eater {
    private final Gender gender;
    private final double speed;
    private final double sightDistance;
    private final double nightSightDistance;
    private final SoundSpectrum soundSpectrum;
    private final double minSoundAmplitude;
    private final Emotion defaultEmotion;
    private final Map<Class<? extends Thing>, Emotion> longTermMemories;
    private final Collection<Organ> organs;
    private final NeuralNetworkConfig neuralNetworkConfig;

    private Brain brain;
    private Velocity movement;
    private PolarCoordinates foodCoordinate;

    @Autowired
    private NeuralNetworkInputsConverter neuralNetworkInputsConverter;

    @Autowired
    private LifeEnvironment environment;

    @Autowired
    private BrainFactory brainFactory;

    @Autowired
    private MeasurementConvertor convertor;

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
     * @param organs             all life form organs.
     */
    public LifeForm(double width, double depth, double mass, Gender gender, double speed, double sightDistance, double nightSightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories, final Collection<Organ> organs) {
        super(width, depth, mass);
        this.gender = gender;
        this.speed = speed;
        this.sightDistance = sightDistance;
        this.nightSightDistance = nightSightDistance;
        this.soundSpectrum = soundSpectrum;
        this.minSoundAmplitude = minSoundAmplitude;
        this.defaultEmotion = defaultEmotion;
        this.longTermMemories = longTermMemories;
        this.organs = Collections.unmodifiableCollection(organs);

        int inputSize = neuralNetworkInputsConverter.inputSize(this.organs);
        int hiddenSize = 1;
        int outputSize = 1;
        double learningRate = 0.01;
        neuralNetworkConfig = new NeuralNetworkConfig(inputSize, hiddenSize, outputSize, learningRate);
    }

    @Override
    public void run() {
        if (brain == null)
            brain = brainFactory.create(this, speed, defaultEmotion, longTermMemories, neuralNetworkConfig, organs);

        foodCoordinate = null;
        movement = null;

//        environment.show(this, convertor.miles2Inches(sightDistance));
//        environment.listen(this, minSoundAmplitude);
        brain.run();
        if (foodCoordinate != null)
            environment.eat(this);
        if (movement != null)
            environment.move(this, movement.azimuth(), movement);
    }

    @Override
    public void movement(Velocity velocity) {
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
