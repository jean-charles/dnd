package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BrainFactoryImpl implements BrainFactory {
    @Autowired
    private ApplicationContext ctx;

    @Override
    public Brain create(LifeForm lifeForm, double maxSpeed, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        return ctx.getBean(DefaultBrain.class, lifeForm, maxSpeed, defaultEmotion, longTermMemories);
    }
}
