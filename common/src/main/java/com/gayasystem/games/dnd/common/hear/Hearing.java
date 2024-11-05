package com.gayasystem.games.dnd.common.hear;

import com.gayasystem.games.dnd.common.Thing;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

public interface Hearing {
    void ear(Thing thing, SoundSpectrum spectrum, double amplitude, PolarCoordinates origin);
}
