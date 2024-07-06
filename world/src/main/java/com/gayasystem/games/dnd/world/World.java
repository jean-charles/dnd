package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.SphericalCoordinate;
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
            var x = new Random().nextDouble() * 10;
            var y = 0.0;
            var z = 0.0;
            things.put(thing, new Coordinate(x, y, z));
        }
    }

    private void move(Thing thing) {
        var coordinate = things.get(thing);
        var velocity = thing.velocity();
        var speed = velocity.speed();
        var destination = velocity.destination();
        if (speed < destination.rho().doubleValue()) {
            var rho = BigDecimal.valueOf(speed);
            destination = new SphericalCoordinate(rho, destination.theta(), destination.phi());
        }
        var newCoordinate = Coordinate.from(destination);
        things.put(thing, newCoordinate);
    }

    @Override
    public void run() {
        for (var thing : things.keySet()) {
            thing.run();
            move(thing);
        }
    }
}
