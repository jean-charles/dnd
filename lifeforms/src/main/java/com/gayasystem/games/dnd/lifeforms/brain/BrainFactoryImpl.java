package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.Thing;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BrainFactoryImpl implements BrainFactory {
    @Override
    public Brain create(Moveable moveable, double speed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        return new DefaultBrain(moveable, speed, attractedBy, scaredBy);
    }
}
