package com.gayasystem.games.dnd.lifeforms.body.organs.muscular;

import com.gayasystem.games.dnd.lifeforms.body.organs.Organ;

public interface MuscularOrgan extends Organ {
    void handleSignals(double[] values);
}
