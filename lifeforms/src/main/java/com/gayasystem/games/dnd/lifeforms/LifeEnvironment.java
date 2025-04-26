package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.lifeforms.sensitive.Hearing;
import com.gayasystem.games.dnd.lifeforms.sensitive.Sighted;
import org.apache.commons.geometry.spherical.oned.Point1S;

public interface LifeEnvironment {
    void addFrom(Thing origin, Thing newThing, Velocity newThingVelocity);

    /**
     * Ask to show to the sighted thing all things in sight distance.
     *
     * @param sighted       thing that want to see.
     * @param sightDistance Sight distance in meters.
     */
    void show(Sighted sighted, double sightDistance);

    void listen(Hearing hearing, double minSoundAmplitude);

    void eat(LifeForm lifeForm);

    void move(Thing thing, Point1S orientation, Velocity velocity);
}
