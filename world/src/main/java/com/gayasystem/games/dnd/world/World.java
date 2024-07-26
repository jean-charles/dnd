package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.LifeEnvironment;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.coordinates.SphericalCoordinate;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;

import java.math.BigDecimal;
import java.util.*;

import static java.lang.Math.PI;

public class World implements Runnable, LifeEnvironment {
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

    @Override
    public void run() {
        for (var thing : thingsCoordinates.keySet()) {
            thing.run();
        }
    }

    @Override
    public void addFrom(Thing origin, Thing newThing, Orientation orientation) {

    }

    @Override
    public void show(Sighted sighted, double sightDistance) {
        var lifeFormCoordinate = thingsCoordinates.get(sighted);
        var lifeFormOrientation = thingsOrientations.get(sighted);

        for (var other : thingsCoordinates.keySet()) {
            if (sighted == other) continue;

            var coordinate = thingsCoordinates.get(other);
            var orientation = thingsOrientations.get(other);

            var distance = coordinate.distanceFrom(lifeFormCoordinate);
            if (distance <= sightDistance) {
                var finalRelativePosition = new SphericalCoordinate(BigDecimal.valueOf(distance), orientation);
                var relativeOrientation = lifeFormOrientation.transpose(orientation);
                sighted.see(other, finalRelativePosition, relativeOrientation);
            }
        }
    }

    @Override
    public void listen(Hearing hearing, double minSoundAmplitude) {

    }

    public void move(Thing thing) {
        var coordinate = thingsCoordinates.get(thing);
        var velocity = thing.velocity();
        if (velocity != null) {
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
    }

    public void add(Thing thing, Coordinate coordinate, Orientation orientation) {
        Objects.requireNonNull(thing, "Parameter 'thing' is null!");
        Objects.requireNonNull(coordinate, "Parameter 'coordinate' is null!");
        Objects.requireNonNull(orientation, "Parameter 'orientation' is null!");

        thingsCoordinates.put(thing, coordinate);
        thingsOrientations.put(thing, orientation);
    }

    /**
     * TEST ONLY
     */
    Coordinate get(Thing thing) {
        return thingsCoordinates.get(thing);
    }
}
