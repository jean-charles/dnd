package com.gayasystem.games.dnd.lifeforms.body.organs.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.Organ;
import com.gayasystem.games.dnd.neuralnetwork.NeuralNetworkConfig;

import java.util.Collection;
import java.util.Map;

public interface BrainFactory {
    Brain create(LifeForm lifeForm, double maxSpeed, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories, NeuralNetworkConfig config, Collection<Organ> organs);
}
