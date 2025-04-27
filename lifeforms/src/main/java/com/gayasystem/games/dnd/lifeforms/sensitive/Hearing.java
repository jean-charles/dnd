package com.gayasystem.games.dnd.lifeforms.sensitive;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.sensitive.stimuli.SoundSpectrum;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

@Deprecated(forRemoval = true)
public interface Hearing {
    void ear(Thing thing, SoundSpectrum spectrum, double amplitude, PolarCoordinates origin);
}
