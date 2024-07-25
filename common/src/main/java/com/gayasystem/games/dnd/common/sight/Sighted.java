package com.gayasystem.games.dnd.common.sight;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.SphericalCoordinate;

public interface Sighted {
    void see(Thing thing, SphericalCoordinate origin);
}
