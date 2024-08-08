package com.gayasystem.games.dnd.common.hear;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;

public interface Hearing {
    void ear(Thing thing, SoundSpectrum spectrum, double amplitude, CircularCoordinate origin);
}
