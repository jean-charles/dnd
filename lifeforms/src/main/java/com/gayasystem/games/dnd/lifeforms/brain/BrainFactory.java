package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;

import java.util.Map;

public interface BrainFactory {
    Brain create(LifeForm lifeForm, double maxSpeed, Map<Class<? extends Thing>, Emotion> longTermMemories);
}
