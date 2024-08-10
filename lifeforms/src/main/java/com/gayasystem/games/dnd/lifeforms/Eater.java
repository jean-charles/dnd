package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;

public interface Eater {
    void foodCoordinate(CircularCoordinate coordinate);

    CircularCoordinate foodCoordinate();

    void eat(Food food);
}
