package com.gayasystem.games.dnd.lifeforms.brain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.neuralnetwork.NeuralNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class BrainFactoryImpl implements BrainFactory {
    @Autowired
    private ApplicationContext ctx;

    @Override
    public Brain create(LifeForm lifeForm, double maxSpeedPerSecond, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        var cfg = lifeForm.neuralNetworkConfig();
        var neuralNetwork = new NeuralNetwork(cfg);
        var om = new ObjectMapper();
        try {
            var clazz = lifeForm.getClass();
            var resource = clazz.getClassLoader().getResource("neuralnetwork/" + clazz.getSimpleName() + ".json");
            var weights = om.readValue(resource, NeuralNetwork.Weights.class);
            neuralNetwork.load(weights);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ctx.getBean(DefaultBrain.class, lifeForm, maxSpeedPerSecond, defaultEmotion, longTermMemories, neuralNetwork);
    }
}
