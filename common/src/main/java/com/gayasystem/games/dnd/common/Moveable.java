package com.gayasystem.games.dnd.common;

public interface Moveable {
    void setDirection(SphericalCoordinate sphericalCoordinate);

    Velocity velocity();
}
