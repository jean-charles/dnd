package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Orientation;
import com.gayasystem.games.dnd.common.SphericalCoordinate;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.images.Image;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.PI;

public class World implements Runnable {
    private Map<Thing, Coordinate> thingsCoordinates = new HashMap<>();
    private Map<Thing, Orientation> thingsOrientations = new HashMap<>();

    public World(Collection<? extends Thing> things) {
        for (var thing : things) {
            var x = new Random().nextDouble() * 10;
            var y = 0.0;
            var z = 0.0;
            thingsCoordinates.put(thing, new Coordinate(x, y, z));
            var orientation = new Orientation(new Random().nextDouble() * 2 * PI, new Random().nextDouble() * 2 * PI);
            thingsOrientations.put(thing, orientation);
        }
    }

    private void show(Thing thing) {
        if (thing instanceof LifeForm) {
            var lifeForm = (LifeForm) thing;
            var sightDistance = lifeForm.sightDistance();

            var lifeFormCoordinate = thingsCoordinates.get(lifeForm);
            var lifeFormOrientation = thingsOrientations.get(lifeForm);

            for (var other : thingsCoordinates.keySet()) {
                if (thing == other) continue;

                var coordinate = thingsCoordinates.get(other);
                var orientation = thingsOrientations.get(other);

                var distance = coordinate.distanceFrom(lifeFormCoordinate);
                if (distance <= sightDistance) {
                    var relativeOrientation = lifeFormOrientation.transpose(orientation);
                    var image = new Image(other.getClass(), relativeOrientation);
                    var finalRelativePosition = new SphericalCoordinate(BigDecimal.valueOf(distance), orientation);
                    lifeForm.see(image, finalRelativePosition);
                }
            }
        }
    }

    private void move(Thing thing) {
        var coordinate = thingsCoordinates.get(thing);
        var velocity = thing.velocity();
        var speed = velocity.speed();
        var destination = velocity.destination();
        if (speed < destination.rho().doubleValue()) {
            var rho = BigDecimal.valueOf(speed);
            destination = new SphericalCoordinate(rho, destination.orientation());
        }
        var relativeCoordinate = Coordinate.from(destination);
        var newCoordinate = coordinate.add(relativeCoordinate);
        thingsCoordinates.put(thing, newCoordinate);
    }

    @Override
    public void run() {
        for (var thing : thingsCoordinates.keySet()) {
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
        return thingsCoordinates.get(thing);
    }
}
