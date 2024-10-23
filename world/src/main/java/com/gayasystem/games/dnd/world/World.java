package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;
import com.gayasystem.games.dnd.lifeforms.LifeEnvironment;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static com.gayasystem.games.dnd.lifeforms.LifeForm.CATCHING_DISTANCE;
import static java.lang.String.format;

@Component
public class World implements Runnable, LifeEnvironment {
    private static final Logger log = LoggerFactory.getLogger(World.class);

    private final Map<Thing, InGameObject> inGameObjects = new HashMap<>();
    private final Map<Coordinate, Thing> thingsByCoordinate = new HashMap<>();
    private final Collection<Thing> thingsToRemove = new ArrayList<>();
    private final Map<Thing, Date> thingsLastMove = new HashMap<>();

    @Autowired
    private MeasurementConvertor convertor;

    private void addThing(Thing thing, Coordinate newCoordinate, Orientation orientation) {
        var previous = inGameObjects.put(thing, new InGameObject(thing, newCoordinate, orientation));
        if (previous != null)
            thingsByCoordinate.remove(previous.coordinate());
        thingsByCoordinate.put(newCoordinate, thing);
    }

    private void removeThing(Thing thing) {
        thingsToRemove.add(thing);
    }

    private void cleanThings() {
        for (var thing : thingsToRemove) {
            var inGameObject = inGameObjects.remove(thing);
            thingsByCoordinate.remove(inGameObject.coordinate());
        }
        thingsToRemove.clear();
    }

    private double distance(Thing thing, Velocity velocity) {
        var timestamps = new Date();
        var lastTimestamps = thingsLastMove.get(thing);
        thingsLastMove.put(thing, timestamps);

        var destination = velocity.destination();
        var speed = velocity.speed();
        var rho = speed;
        if (lastTimestamps != null) {
            double interval = (timestamps.getTime() - lastTimestamps.getTime()) / 1000.0;
            var distance = interval * speed;
            rho = destination.rho().doubleValue();
            log.debug(format("speed=%.2f', interval=%.2fs, distance=%.2f', rho=%.2f'", speed, interval * 1000, distance, rho));
            rho = (rho <= distance) ? rho - CATCHING_DISTANCE : distance;
        }
        return rho;
    }

    private Thing catchThing(LifeForm lifeForm, CircularCoordinate targetRelativeCoordinate) {
        if (targetRelativeCoordinate.rho().compareTo(BigDecimal.valueOf(CATCHING_DISTANCE)) > 0)
            return null;
        var catcher = inGameObjects.get(lifeForm);
        var catcherCoordinate = catcher.coordinate();
        var targetCoordinate = catcherCoordinate.from(targetRelativeCoordinate);
        var target = thingsByCoordinate.get(targetCoordinate);
        return target;
    }

    @Override
    public void run() {
        for (var thing : inGameObjects.keySet()) {
            thing.run();
        }
        cleanThings();
    }

    @Override
    public void show(Sighted sighted, double sightDistance) {
        var obj = inGameObjects.get((Thing) sighted);
        var lifeFormCoordinate = obj.coordinate();
        var lifeFormOrientation = obj.orientation();

        for (var other : inGameObjects.keySet()) {
            if (sighted == other) continue;

            var sightedObj = inGameObjects.get((Thing) other);
            var targetCcoordinate = sightedObj.coordinate();

            var distance = targetCcoordinate.distanceFrom(lifeFormCoordinate);
            if (distance <= sightDistance) {
                var targetOrientation = sightedObj.orientation();

                var finalRelativeCoordinate = lifeFormCoordinate.to(targetCcoordinate);
                var relativeOrientation = lifeFormOrientation.transpose(targetOrientation);
                sighted.see(other, finalRelativeCoordinate, relativeOrientation);
            }
        }
    }

    @Override
    public void listen(Hearing hearing, double minSoundAmplitude) {

    }

    @Override
    public void addFrom(Thing origin, Thing newThing, Orientation orientation) {
        var obj = inGameObjects.get(origin);
        var originCoordinate = obj.coordinate();
        var originOrientation = obj.orientation();
        var newThingOrientation = orientation.transpose(originOrientation);

        addThing(newThing, originCoordinate, newThingOrientation);
    }

    @Override
    public void move(Thing thing) {
        var velocity = thing.velocity();
        if (velocity != null) {
            var rho = distance(thing, velocity);
            var destination = new CircularCoordinate(rho, velocity.destination().orientation());
            var obj = inGameObjects.get(thing);
            var coordinate = obj.coordinate();

            var newCoordinate = coordinate.from(destination);
            var orientation = thing.rotation();
            addThing(thing, newCoordinate, orientation);
        }
    }

    @Override
    public void eat(LifeForm lifeForm) {
        var foodCoordinate = lifeForm.foodCoordinate();
        if (foodCoordinate == null)
            return;
        var food = catchThing(lifeForm, foodCoordinate);
        if (food == null)
            return;
        removeThing(food);
        lifeForm.eat((Food) food);
    }

    public void add(Thing thing, Coordinate coordinate, Orientation orientation) {
        Objects.requireNonNull(thing, "Parameter 'thing' is null!");
        Objects.requireNonNull(coordinate, "Parameter 'coordinate' is null!");
        Objects.requireNonNull(orientation, "Parameter 'orientation' is null!");

        addThing(thing, coordinate, orientation);
    }

    public Collection<InGameObject> objects() {
        return inGameObjects.values();
    }

    /**
     * TEST ONLY
     */
    Coordinate getThingCoordinate(Thing thing) {
        return inGameObjects.get(thing).coordinate();
    }
}
