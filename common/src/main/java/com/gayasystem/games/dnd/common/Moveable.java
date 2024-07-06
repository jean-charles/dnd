package com.gayasystem.games.dnd.common;

public interface Moveable {
    void setDirection(SpericalCoordinate spericalCoordinate);

    Velocity velocity();
}
