package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.Orientation;

public record Object3D(Thing thing, Coordinate coordinate, Orientation orientation) {
}
