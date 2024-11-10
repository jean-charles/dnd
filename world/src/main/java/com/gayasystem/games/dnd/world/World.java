package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.hear.Hearing;
import com.gayasystem.games.dnd.common.sight.Sighted;
import com.gayasystem.games.dnd.lifeforms.LifeEnvironment;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.world.services.InGameObjectsManager;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
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
            manager.move(thing);
        }
        manager.clean();
    }

    @Override
    public void show(Sighted sighted, double sightDistance) {
        if (!(sighted instanceof Thing)) return;

        for (var other : manager.getAllThings()) {
            if (sighted == other) continue;

            var relativeCoordinate = manager.relativeCoordinates((Thing) sighted, other);
            if (relativeCoordinate.getRadius() <= sightDistance) {
                sighted.see(other, relativeCoordinate, 0);
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

        manager.add(newThing, originCoordinate, newThingVelocity.azimuth());
    }

    @Override
    public void move(Thing thing, Point1S orientation, Velocity velocity) {
        manager.move(thing, orientation, velocity);
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

        manager.add(thing, coordinate, Point1S.of(orientation));
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
}
