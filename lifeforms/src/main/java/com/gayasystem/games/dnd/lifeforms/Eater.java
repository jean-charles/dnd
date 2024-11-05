package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Food;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

public interface Eater {
    void foodCoordinate(PolarCoordinates coordinate);

    PolarCoordinates foodCoordinate();

    void eat(Food food);
}
