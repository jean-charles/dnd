package com.gayasystem.games.dnd.common;

public interface Moveable {
    void setVelocity(SphericalCoordinate destination, double speed);

    Velocity velocity();
}
