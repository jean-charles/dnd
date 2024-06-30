package com.gayasystem.games.dnd.ecosystem.food;

public class Carrot implements Food {
    @Override
    public double mass() {
        return 0.13;
    }

    @Override
    public double nourishment() {
        return 20;
    }
}
