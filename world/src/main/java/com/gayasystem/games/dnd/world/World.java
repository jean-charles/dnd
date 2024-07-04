package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class World implements Runnable {
    private Map<Thing, Coordinate> things = new HashMap<>();

    public World(Collection<Thing> newThings) {
        for (var thing : newThings) {
            var x = BigDecimal.valueOf(new Random().nextDouble() * 10);
            var y = BigDecimal.valueOf(0);
            var z = BigDecimal.valueOf(0);
            things.put(thing, new Coordinate(x, y, z));
        }
    }

    @Override
    public void run() {
        for (var thing : things.keySet()) {
            thing.run();
        }
    }
}
