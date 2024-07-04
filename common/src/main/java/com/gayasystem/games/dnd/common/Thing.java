package com.gayasystem.games.dnd.common;

public interface Thing extends Moveable, Runnable {
    /**
     * The mass of the thing in pound (lb).
     *
     * @return the mass in pound
     */
    double mass();
}
