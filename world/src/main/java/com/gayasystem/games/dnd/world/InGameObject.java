package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import org.apache.commons.geometry.euclidean.twod.Vector2D;

public record InGameObject(Thing thing, Vector2D coordinate, Velocity velocity) {
}
