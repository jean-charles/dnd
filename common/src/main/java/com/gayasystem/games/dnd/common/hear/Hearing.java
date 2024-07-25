package com.gayasystem.games.dnd.common.hear;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.SphericalCoordinate;

public interface Hearing {
    void ear(Thing thing, SoundSpectrum spectrum, double amplitude, SphericalCoordinate origin);
}
