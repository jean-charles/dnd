package com.gayasystem.games.dnd.world.services.domains;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;

public record InGameObject(Thing thing, Vector2D coordinate, Point1S orientation, Velocity velocity) {
}
