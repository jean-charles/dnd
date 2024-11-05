package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.world.InGameObject;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.gayasystem.games.dnd.lifeforms.LifeForm.CATCHING_DISTANCE;
import static java.lang.Math.min;

@Service
public class InGameObjectsManager {
    private final Map<Thing, InGameObject> inGameObjects = new HashMap<>();
    private final Map<Vector2D, Thing> thingsByCoordinate = new HashMap<>();
    private final Map<Thing, Date> thingsLastMove = new HashMap<>();
    private final Collection<Thing> thingsToRemove = new ArrayList<>();

    @Autowired
    private HitBoxValidator validator;

    private boolean doesItWantToMove(Velocity velocity) {
        if (velocity == null) return false;
        if (velocity.speed() == 0) return false;
        var destination = velocity.destination();
        if (destination.rho().equals(BigDecimal.ZERO)) return false;

        return true;
    }

    private boolean doesItWantToRotate(Velocity velocity) {
        if (velocity == null) return false;
        var destination = velocity.destination();
        if (destination.orientation().phi().compareTo(BigDecimal.ZERO) == 0) return false;

        return true;
    }

    private double relativeDistance(double rho, double speed, double interval) {
        var distance = speed * interval;

        return min(rho - CATCHING_DISTANCE, distance);
    }

    /**
     * Add or move an {@link InGameObject in game objet} with the {@link Thing thing} at the {@link Vector2D coordinate}
     * and the {@link Velocity velocity}.
     *
     * @param thing      {@link Thing} to add in the {@link InGameObject in game objet}.
     * @param coordinate {@link Vector2D} where to put the {@link InGameObject in game objet}.
     * @param velocity   {@link Velocity} of the {@link InGameObject in game objet} in the world.
     */
    private void add(Thing thing, Vector2D coordinate, Velocity velocity) {
        var previous = inGameObjects.put(thing, new InGameObject(thing, coordinate, velocity));
        if (previous != null)
            thingsByCoordinate.remove(previous.coordinate());
        thingsByCoordinate.put(coordinate, thing);
        thingsLastMove.computeIfAbsent(thing, k -> new Date());
    }

    /**
     * Create a new {@link InGameObject in game objet} with the {@link Thing thing} at the {@link Vector2D coordinate}
     * and the {@link Orientation orientation}.
     *
     * @param thing       {@link Thing} to add in the {@link InGameObject in game objet}.
     * @param coordinate  {@link Vector2D} where to put the {@link InGameObject in game objet}.
     * @param orientation Orientation of the {@link InGameObject in game objet} in the world.
     */
    public void add(Thing thing, Vector2D coordinate, double orientation) {
        var velocity = new Velocity(0, PolarCoordinates.of(0, orientation));
        add(thing, coordinate, velocity);
    }

    /**
     * Get {@link Thing thing} from its coordinates.
     *
     * @param from {@link Vector2D} from where to get the {@link Thing thing}.
     * @return {@link Thing thing} found at the {@link Vector2D coordinate} or {@code null} if not found.
     */
    public Thing getThing(Vector2D from) {
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
     * Move the {@link Thing thing} to the destination relatively to the elapsed time since the last move and others
     * {@link Thing things}.
     *
     * @param thing    {@link Thing} witch to calculate the distance.
     * @param velocity Wanted {@link Velocity destination} by the {@link Thing thing}.
     */
    public void move(Thing thing, Velocity velocity) {
        var timestamps = new Date();
        var lastTimestamps = thingsLastMove.get(thing);
        thingsLastMove.put(thing, timestamps);

        var inGameObj = inGameObjects.get(thing);
        var wantedRotation = inGameObj.velocity().destination().orientation().phi().doubleValue();
        var newPhi = velocity.destination().orientation().phi().doubleValue();
        if (doesItWantToRotate(velocity)) {
            for (var other : inGameObjects.values()) {
                if (inGameObj != other) {
                    var rotation = validator.rotation(inGameObj, other, wantedRotation);
                    if (wantedRotation > 0.0 && rotation < newPhi) {
                        newPhi = rotation;
                    } else if (wantedRotation < 0.0 && rotation > newPhi) {
                        newPhi = rotation;
                    }
                }
            }
        }
        PolarCoordinates newDestination = velocity.destination();
        if (doesItWantToMove(velocity)) {
            double interval = (timestamps.getTime() - lastTimestamps.getTime()) / 1000.0;
            double rho = newDestination.rho().doubleValue();
            rho = relativeDistance(rho, velocity.speed(), interval);

            for (var other : inGameObjects.values()) {
                if (inGameObj != other) {
                    var distance = validator.translate(inGameObj, other, rho);
                    if (distance < rho) rho = distance;
                }
            }
            newDestination = new PolarCoordinates(rho, newPhi);
        }
        var newVelocity = new Velocity(velocity.speed(), newDestination);

        var coordinate = inGameObj.coordinate();
        var newCoordinate = coordinate.from(newDestination);
        add(thing, newCoordinate, newVelocity);
    }

    /**
     * {@link LifeForm} try to catch a {@link Thing thing} at {@link PolarCoordinates relative coordinate}.
     *
     * @param lifeForm                 {@link LifeForm} that try to catch a {@link Thing thing}.
     * @param targetRelativeCoordinate {@link PolarCoordinates Relative coordinate} where to catch the
     *                                 {@link Thing thing}.
     * @return The {@link Thing caught thing} or null.
     */
    public Thing catchThing(LifeForm lifeForm, PolarCoordinates targetRelativeCoordinate) {
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

    Date getLastTimestamp(Thing of) {
        return thingsLastMove.get(of);
    }
}
