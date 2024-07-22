package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BrainFactoryImpl implements BrainFactory {
    @Override
    public Brain create(LifeForm lifeForm, double speed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        return new DefaultBrain(lifeForm, speed, attractedBy, scaredBy);
    }
}
