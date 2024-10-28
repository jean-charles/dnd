package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.gayasystem.games.dnd.lifeforms.LifeForm.CATCHING_DISTANCE;

@Service
public class InGameObjectsManager {
    private final Map<Thing, InGameObject> inGameObjects = new HashMap<>();
    private final Map<Coordinate, Thing> thingsByCoordinate = new HashMap<>();
    private final Map<Thing, Date> thingsLastMove = new HashMap<>();
    private final Collection<Thing> thingsToRemove = new ArrayList<>();

    /**
     * Create a new {@link InGameObject in game objet} with the {@link Thing thing} at the {@link Coordinate coordinate}
     * and the {@link Orientation orientation}.
     *
     * @param thing         {@link Thing} to add in the {@link InGameObject in game objet}.
     * @param newCoordinate {@link Coordinate} where to put the {@link InGameObject in game objet}.
     * @param orientation   {@link Orientation} of the {@link InGameObject in game objet} in the world.
     */
    public void add(Thing thing, Coordinate newCoordinate, Orientation orientation) {
        var previous = inGameObjects.put(thing, new InGameObject(thing, newCoordinate, orientation));
        if (previous != null)
            thingsByCoordinate.remove(previous.coordinate());
        thingsByCoordinate.put(newCoordinate, thing);
        thingsLastMove.computeIfAbsent(thing, k -> new Date());
    }

    /**
     * Get {@link Thing thing} from its coordinates.
     *
     * @param from {@link Coordinate} from where to get the {@link Thing thing}.
     * @return {@link Thing thing} found at the {@link Coordinate coordinate} or {@code null} if not found.
     */
    public Thing getThing(Coordinate from) {
        return thingsByCoordinate.get(from);
    }

    /**
     * Mark the {@link Thing thing} to be removed during the main loop.
     *
     * @param thing the {@link Thing thing} marked to be removed.
     */
    public void removeThing(Thing thing) {
        thingsToRemove.add(thing);
    }

    /**
     * Clean all {@link InGameObject in game objet} marked to be removed with {@link #removeThing(Thing) the method
     * remove}.
     */
    public void clean() {
        for (var thing : thingsToRemove) {
            var inGameObject = inGameObjects.remove(thing);
            thingsByCoordinate.remove(inGameObject.coordinate());
            thingsLastMove.remove(thing);
        }
        thingsToRemove.clear();
    }

    /**
     * Get the {@link InGameObject in game objet} that match the {@link Thing thing}.
     *
     * @param thing {@link Thing} that match the {@link InGameObject in game objet}.
     * @return The matched {@link InGameObject in game objet} or null if not found.
     */
    public InGameObject get(Thing thing) {
        return inGameObjects.get(thing);
    }

    /**
     * Get all {@link InGameObject in game objets}, including the ones to be removed.
     *
     * @return all {@link InGameObject in game objets}.
     */
    public Collection<InGameObject> getAll() {
        return inGameObjects.values();
    }

    /**
     * Get all {@link Thing things}, including the ones to be removed.
     *
     * @return All {@link Thing things} of the {@link InGameObject in game objets}.
     */
    public Set<Thing> getAllThings() {
        return inGameObjects.keySet();
    }

    /**
     * Calculate the distance to the destination relatively to the elapsed time since the last move.
     *
     * @param thing    {@link Thing} witch to calculate the distance.
     * @param velocity {@link Velocity} of the {@link Thing}.
     * @return the distance to move.
     */
    public double distance(Thing thing, Velocity velocity) {
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
            rho = (rho <= distance) ? rho - CATCHING_DISTANCE : distance;
        }
        return rho;
    }

    /**
     * {@link LifeForm} try to catch a {@link Thing thing} at {@link CircularCoordinate relative coordinate}.
     *
     * @param lifeForm                 {@link LifeForm} that try to catch a {@link Thing thing}.
     * @param targetRelativeCoordinate {@link CircularCoordinate Relative coordinate} where to catch the
     *                                 {@link Thing thing}.
     * @return The {@link Thing caught thing} or null.
     */
    public Thing catchThing(LifeForm lifeForm, CircularCoordinate targetRelativeCoordinate) {
        if (targetRelativeCoordinate.rho().compareTo(BigDecimal.valueOf(CATCHING_DISTANCE)) > 0)
            return null;
        var catcher = this.get(lifeForm);
        var catcherCoordinate = catcher.coordinate();
        var targetCoordinate = catcherCoordinate.from(targetRelativeCoordinate);
        var target = (Food) this.getThing(targetCoordinate);
        return target;
    }

    /* TESTS ONLY */
    int sizeOfInGameObjects() {
        return inGameObjects.size();
    }

    int sizeOfThingsByCoordinate() {
        return thingsByCoordinate.size();
    }

    int sizeOfThingsLastMove() {
        return thingsLastMove.size();
    }

    int sizeOfThingsToRemove() {
        return thingsToRemove.size();
    }
}
