package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;

import java.util.HashMap;
import java.util.Map;

public class World implements Runnable {
    private Map<Thing, Coordinate> things = new HashMap<>();

    @Override
    public void run() {
        for (var thing : things.keySet()) {
            thing.run();
        }
    }
}
