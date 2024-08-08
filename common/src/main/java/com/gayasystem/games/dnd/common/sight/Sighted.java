package com.gayasystem.games.dnd.common.sight;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;

public interface Sighted {
    void see(Thing thing, CircularCoordinate origin, Orientation orientation);
}
