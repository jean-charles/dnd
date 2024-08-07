package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.LifeEnvironment;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.coordinates.SphericalCoordinate;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class World implements Runnable, LifeEnvironment {
    private Map<Thing, InGameObject> things = new HashMap<>();

    private void move(Thing thing) {
        var velocity = thing.velocity();
        if (velocity != null) {
            var speed = velocity.speed();
            var destination = velocity.destination();
            if (speed < destination.rho().doubleValue()) {
                var rho = BigDecimal.valueOf(speed);
                destination = new SphericalCoordinate(rho, destination.orientation());
            }
            var obj = things.get(thing);
            var coordinate = obj.coordinate();
            var orientation = obj.orientation();

            var relativeCoordinate = Coordinate.from(destination);
            var newCoordinate = coordinate.add(relativeCoordinate);
            things.put(thing, new InGameObject(thing, newCoordinate, orientation));
        }
    }

    @Override
    public void run() {
        for (var thing : things.keySet()) {
            thing.run();
            var velocity = thing.velocity();
            move(thing);
        }
    }

    @Override
    public void addFrom(Thing origin, Thing newThing, Orientation orientation) {
        var obj = things.get(origin);
        var originCoordinate = obj.coordinate();
        var originOrientation = obj.orientation();
        var newThingOrientation = orientation.transpose(originOrientation);
        things.put(newThing, new InGameObject(newThing, originCoordinate, newThingOrientation));
    }

    @Override
    public void show(Sighted sighted, double sightDistance) {
        var obj = things.get((Thing) sighted);
        var lifeFormCoordinate = obj.coordinate();
        var lifeFormOrientation = obj.orientation();

        for (var other : things.keySet()) {
            if (sighted == other) continue;

            var sightedObj = things.get((Thing) other);
            var coordinate = sightedObj.coordinate();
            var orientation = sightedObj.orientation();

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

    public void add(Thing thing, Coordinate coordinate, Orientation orientation) {
        Objects.requireNonNull(thing, "Parameter 'thing' is null!");
        Objects.requireNonNull(coordinate, "Parameter 'coordinate' is null!");
        Objects.requireNonNull(orientation, "Parameter 'orientation' is null!");

        things.put(thing, new InGameObject(thing, coordinate, orientation));
    }

    public Collection<InGameObject> objects() {
        return things.values();
    }

    /**
     * TEST ONLY
     */
    Coordinate get(Thing thing) {
        return things.get(thing).coordinate();
    }
}
