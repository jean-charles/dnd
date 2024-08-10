package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;
import com.gayasystem.games.dnd.lifeforms.LifeEnvironment;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;

@Component
public class World implements Runnable, LifeEnvironment {
    private Map<Thing, InGameObject> inGameObjects = new HashMap<>();
    private Map<Coordinate, Thing> thingsByCoordinate = new HashMap<>();

    private void addThing(Thing thing, Coordinate newCoordinate, Orientation orientation) {
        var previous = inGameObjects.put(thing, new InGameObject(thing, newCoordinate, orientation));
        if (previous != null)
            thingsByCoordinate.remove(previous.coordinate());
        thingsByCoordinate.put(newCoordinate, thing);
    }

    private void removeThing(Thing thing) {
        var inGameObject = inGameObjects.remove(thing);
        thingsByCoordinate.remove(inGameObject.coordinate());
    }

    private void move(Thing thing) {
        var velocity = thing.velocity();
        if (velocity != null) {
            var speed = velocity.speed();
            var destination = velocity.destination();
            if (speed < destination.rho().doubleValue()) {
                var rho = BigDecimal.valueOf(speed);
                destination = new CircularCoordinate(rho, destination.orientation());
            }
            var obj = inGameObjects.get(thing);
            var coordinate = obj.coordinate();
            var orientation = obj.orientation();

            var relativeCoordinate = coordinate.from(destination);
            var newCoordinate = relativeCoordinate;//coordinate.add(relativeCoordinate);
            addThing(thing, newCoordinate, orientation);
        }
    }

    private void eat(Thing thing) {
        if (!(thing instanceof LifeForm))
            return;
        var lifeForm = (LifeForm) thing;
        var foodCoordinate = lifeForm.foodCoordinate();
        if (foodCoordinate == null)
            return;
        var food = catchThing(lifeForm, foodCoordinate);
        if (food == null)
            return;
        removeThing(food);
        lifeForm.eat((Food) food);
    }

    private Thing catchThing(LifeForm lifeForm, CircularCoordinate targetRelativeCoordinate) {
        if (targetRelativeCoordinate.rho().compareTo(ZERO) != 0)
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
            move(thing);
            eat(thing);
        }
    }

    @Override
    public void show(Sighted sighted, double sightDistance) {
        var obj = inGameObjects.get((Thing) sighted);
        var lifeFormCoordinate = obj.coordinate();
        var lifeFormOrientation = obj.orientation();

        for (var other : inGameObjects.keySet()) {
            if (sighted == other) continue;

            var sightedObj = inGameObjects.get((Thing) other);
            var coordinate = sightedObj.coordinate();
            var orientation = sightedObj.orientation();

            var distance = coordinate.distanceFrom(lifeFormCoordinate);
            if (distance <= sightDistance) {
                var finalRelativePosition = new CircularCoordinate(BigDecimal.valueOf(distance), orientation);
                var relativeOrientation = lifeFormOrientation.transpose(orientation);
                sighted.see(other, finalRelativePosition, relativeOrientation);
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
