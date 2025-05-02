package com.gayasystem.games.dnd.lifeforms.body.organs.sensitives;

import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Pressure;

public class Hair extends AbstractSensitiveOrgan<Pressure> {
    public Hair() {
        super(Sense.Touch);
    }

    @Override
    public int nbSignals() {
        return 0;
    }

    @Override
    public void stimulate(final Pressure pressure) {
        double[] stimuli = new double[1];
        stimuli[0] = pressure.pressure();
        stimulate(stimuli);
    }
}