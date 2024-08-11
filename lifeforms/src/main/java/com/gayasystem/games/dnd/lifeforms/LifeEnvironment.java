package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;

public interface LifeEnvironment {
    void addFrom(Thing origin, Thing newThing, Orientation orientation);

    void show(Sighted sighted, double sightDistance);

    void listen(Hearing hearing, double minSoundAmplitude);

    void eat(LifeForm lifeForm);

    void move(Thing thing);
}
