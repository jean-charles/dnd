package com.gayasystem.games.dnd.common;

public interface Moveable {
    void setDestination(SphericalCoordinate destination, double speed);

    Velocity velocity();
}
