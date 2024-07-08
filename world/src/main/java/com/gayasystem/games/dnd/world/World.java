package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.PI;

public class World implements Runnable {
    private Map<Thing, Coordinate> things = new HashMap<>();
    private Map<Thing, SphericalCoordinate> thingsOrientation = new HashMap<>();

    public World(Collection<Thing> newThings) {
        for (var thing : newThings) {
            var x = new Random().nextDouble() * 10;
            var y = 0.0;
            var z = 0.0;
            things.put(thing, new Coordinate(x, y, z));
            var orientation = new SphericalCoordinate(0, new Random().nextDouble() * 2 * PI, new Random().nextDouble() * 2 * PI);
            thingsOrientation.put(thing, orientation);
        }
    }

    private void show(Thing thing) {
        if (thing instanceof LifeForm) {

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
        var relativeCoordinate = Coordinate.from(destination);
        var newCoordinate = coordinate.add(relativeCoordinate);
        things.put(thing, newCoordinate);
    }

    @Override
    public void run() {
        for (var thing : things.keySet()) {
            // Show all visible things to this thing
            show(thing);
            // Run the thing
            thing.run();
            // Move th thing
            move(thing);
        }
    }

    /**
     * TEST ONLY
     */
    Coordinate get(Thing thing) {
        return things.get(thing);
    }
}
