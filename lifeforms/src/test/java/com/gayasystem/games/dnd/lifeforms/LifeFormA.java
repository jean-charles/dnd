package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.neuralnetwork.NeuralNetworkConfig;
import org.springframework.stereotype.Component;

import static com.gayasystem.games.dnd.lifeforms.LifeFormTest.*;

@Component
public class LifeFormA extends LifeForm {
    public LifeFormA() {
        super(0, 0, MASS, GENDER, SPEED, SIGHT_DISTANCE, MAX_SPEED, SOUND_SPECTRUM, MIN_SOUND_AMPLITUDE, DEFAULT_EMOTION, MEMORIES, null);
    }

    @Override
    public NeuralNetworkConfig neuralNetworkConfig() {
        return null;
    }
}
