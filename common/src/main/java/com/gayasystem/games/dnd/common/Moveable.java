package com.gayasystem.games.dnd.common;

public interface Moveable {
    void setDirection(Direction direction);

    Velocity velocity();
}
