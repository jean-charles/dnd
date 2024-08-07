package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.Orientation;

public record InGameObject(Thing thing, Coordinate coordinate, Orientation orientation) {
}
