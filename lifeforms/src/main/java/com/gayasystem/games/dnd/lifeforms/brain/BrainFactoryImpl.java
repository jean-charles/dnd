package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BrainFactoryImpl implements BrainFactory {
    @Autowired
    private ApplicationContext ctx;

    @Override
    public Brain create(LifeForm lifeForm, double maxSpeed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        return ctx.getBean(DefaultBrain.class, lifeForm, maxSpeed, attractedBy, scaredBy);
    }
}
