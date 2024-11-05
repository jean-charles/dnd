package com.gayasystem.games.dnd.lifeforms.brain.memories;


import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

public record SpatialEngram(Engram engram, PolarCoordinates origin) {
}
