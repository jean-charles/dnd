package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.common.Moveable;
import com.gayasystem.games.dnd.common.Thing;

import java.util.Collection;

public interface BrainFactory {
    Brain create(Moveable moveable, double speed, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy);
}
