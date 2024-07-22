package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;

import java.util.Collection;

public class DefaultBrain extends AbstractBrain {
    public DefaultBrain(LifeForm lifeForm, double speed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        super(lifeForm, speed, attractedBy, scaredBy);
    }
}
