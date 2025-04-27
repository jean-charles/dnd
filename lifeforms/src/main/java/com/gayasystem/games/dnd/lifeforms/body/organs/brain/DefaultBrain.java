package com.gayasystem.games.dnd.lifeforms.body.organs.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.Organ;
import com.gayasystem.games.dnd.neuralnetwork.NeuralNetwork;
import com.gayasystem.games.dnd.neuralnetwork.NeuralNetworkConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
@Scope("prototype")
public class DefaultBrain extends AbstractBrain {
    public DefaultBrain(LifeForm lifeForm, double maxSpeed, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories, NeuralNetwork neuralNetwork, final NeuralNetworkConfig config, final Collection<Organ> organs) {
        super(lifeForm, maxSpeed, defaultEmotion, longTermMemories, neuralNetwork, config, organs);
    }
}
