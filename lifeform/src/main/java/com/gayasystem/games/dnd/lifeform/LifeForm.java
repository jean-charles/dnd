package com.gayasystem.games.dnd.lifeform;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;

public interface LifeForm extends Thing {
    void see(Image img);

    void ear(Sound img);

    void touchedBy(Velocity velocity, Thing thing);

    double sightDistance();
}
