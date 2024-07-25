package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;

public interface LifeEnvironment {
    void show(Sighted sighted, double sightDistance);

    void listen(Hearing hearing, double minSoundAmplitude);
}
