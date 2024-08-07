package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;

import java.util.Collection;

public interface BrainFactory {
    Brain create(LifeForm lifeForm, double maxSpeed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy);
}
