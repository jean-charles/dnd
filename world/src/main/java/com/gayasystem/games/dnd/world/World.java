package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;
import com.gayasystem.games.dnd.lifeforms.LifeEnvironment;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.world.services.InGameObjectsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class World implements Runnable, LifeEnvironment {
    private static final Logger log = LoggerFactory.getLogger(World.class);

    @Autowired
    private InGameObjectsManager manager;

    @Override
    public void run() {
        for (var thing : manager.getAllThings()) {
            thing.run();
        }
        manager.clean();
    }

    @Override
    public void show(Sighted sighted, double sightDistance) {
        var obj = manager.get((Thing) sighted);
        var lifeFormCoordinate = obj.coordinate();
        var lifeFormOrientation = obj.orientation();

        // TODO: Select only object in sight distance
        for (var other : manager.getAllThings()) {
            if (sighted == other) continue;

            var sightedObj = manager.get((Thing) other);
            var targetCcoordinate = sightedObj.coordinate();

            var distance = targetCcoordinate.distanceFrom(lifeFormCoordinate);
            if (distance <= sightDistance) {
                var targetOrientation = sightedObj.orientation();

                var finalRelativeCoordinate = lifeFormCoordinate.to(targetCcoordinate);
                var relativeOrientation = lifeFormOrientation.transpose(targetOrientation);
                // TODO: see only visible things
                sighted.see(other, finalRelativeCoordinate, relativeOrientation);
            }
        }
    }

    @Override
    public void listen(Hearing hearing, double minSoundAmplitude) {
    }

    @Override
    public void addFrom(Thing origin, Thing newThing, Orientation orientation) {
        var obj = manager.get(origin);
        var originCoordinate = obj.coordinate();
        var originOrientation = obj.orientation();
        var newThingOrientation = orientation.transpose(originOrientation);

        manager.add(newThing, originCoordinate, newThingOrientation);
    }

    @Override
    public void move(Thing thing) {
        var velocity = thing.velocity();
        if (velocity != null) {
            var rho = manager.distance(thing, velocity);
            var destination = new CircularCoordinate(rho, velocity.destination().orientation());
            var obj = manager.get(thing);
            var coordinate = obj.coordinate();

            var newCoordinate = coordinate.from(destination);
            var orientation = thing.rotation();
            manager.add(thing, newCoordinate, orientation);
        }
    }

    @Override
    public void eat(LifeForm lifeForm) {
        var foodCoordinate = lifeForm.foodCoordinate();
        if (foodCoordinate == null)
            return;
        var food = manager.catchThing(lifeForm, foodCoordinate);
        if (food == null)
            return;
        manager.removeThing(food);
        lifeForm.eat((Food) food);
    }

    public void add(Thing thing, Coordinate coordinate, Orientation orientation) {
        Objects.requireNonNull(thing, "Parameter 'thing' is null!");
        Objects.requireNonNull(coordinate, "Parameter 'coordinate' is null!");
        Objects.requireNonNull(orientation, "Parameter 'orientation' is null!");

        manager.add(thing, coordinate, orientation);
    }

    public Collection<InGameObject> objects() {
        return manager.getAll();
    }

    /**
     * TEST ONLY
     */
    Coordinate getThingCoordinate(Thing thing) {
        return manager.get(thing).coordinate();
    }
}
