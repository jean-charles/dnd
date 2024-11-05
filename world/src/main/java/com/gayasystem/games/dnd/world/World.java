package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;
import com.gayasystem.games.dnd.lifeforms.LifeEnvironment;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.world.services.InGameObjectsManager;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
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
        var lifeFormVelocity = obj.velocity();

        // TODO: Select only object in sight distance
        for (var other : manager.getAllThings()) {
            if (sighted == other) continue;

            var sightedObj = manager.get(other);
            var sightedObjCoordinate = sightedObj.coordinate();
            var distance = lifeFormCoordinate.distance(sightedObjCoordinate);
            if (distance <= sightDistance) {
                var targetOrientation = sightedObj.velocity().destination().getAzimuth();

                var relativeCoordinate = lifeFormCoordinate.directionTo(sightedObjCoordinate);
//                var relativeOrientation = lifeFormVelocity.destination().getAzimuth().transpose(targetOrientation);
                // TODO: see only visible things
                sighted.see(other, null, 0);
            }
        }
    }

    @Override
    public void listen(Hearing hearing, double minSoundAmplitude) {
    }

    @Override
    public void addFrom(Thing origin, Thing newThing, Velocity newThingVelocity) {
        var obj = manager.get(origin);
        var originCoordinate = obj.coordinate();

        manager.add(newThing, originCoordinate, newThingVelocity.destination().getAzimuth());
    }

    @Override
    public void move(Thing thing, Velocity velocity) {
        manager.move(thing, velocity);
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

    public void add(Thing thing, Vector2D coordinate, double orientation) {
        Objects.requireNonNull(thing, "Parameter 'thing' is null!");
        Objects.requireNonNull(coordinate, "Parameter 'coordinate' is null!");

        manager.add(thing, coordinate, orientation);
    }

    public Collection<InGameObject> objects() {
        return manager.getAll();
    }

    /**
     * TEST ONLY
     */
    Vector2D getThingCoordinate(Thing thing) {
        return manager.get(thing).coordinate();
    }


//    public Coordinate from(PolarCoordinates sc) {
//        double rho = sc.rho().doubleValue();
//        double phi = sc.orientation().phi().doubleValue();
//
//        double x = this.x.doubleValue() + rho * cos(phi);
//        double y = this.y.doubleValue() + rho * sin(phi);
//
//        return new Coordinate(x, y);
//    }
//    public PolarCoordinates to(Coordinate coordinate) {
//        double x = coordinate.x.subtract(this.x).doubleValue();
//        double y = coordinate.y.subtract(this.y).doubleValue();
//
//        double rho = hypot(x, y);
//        double phi = atan2(y, x);
//        return PolarCoordinates.of(rho, phi);
//    }
//    public double distanceFrom(Coordinate formCoordinate) {
//        var newX = pow(x.doubleValue() - formCoordinate.x.doubleValue(), 2);
//        var newY = pow(y.doubleValue() - formCoordinate.y.doubleValue(), 2);
//
//        return sqrt(newX + newY);
//    }
}
