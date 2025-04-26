package com.gayasystem.games.dnd.lifeforms.sensitive;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.sensitive.hearring.SoundSpectrum;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

public interface Hearing {
    void ear(Thing thing, SoundSpectrum spectrum, double amplitude, PolarCoordinates origin);
}
