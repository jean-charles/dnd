package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class DefaultBrain extends AbstractBrain {
    public DefaultBrain(LifeForm lifeForm, double maxSpeed, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        super(lifeForm, maxSpeed, defaultEmotion, longTermMemories);
    }
}
