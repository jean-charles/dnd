package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Food;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.world.InGameObject;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.gayasystem.games.dnd.lifeforms.LifeForm.CATCHING_DISTANCE;

@Service
public class InGameObjectsManager {
    private final Map<Thing, InGameObject> inGameObjects = new HashMap<>();
    private final Map<Vector2D, Thing> thingsByCoordinate = new HashMap<>();
    private final Map<Thing, Date> thingsLastMove = new HashMap<>();
    private final Collection<Thing> thingsToRemove = new ArrayList<>();

    @Autowired
    private PhysicalService physical;

    @Autowired
    private HitBoxUtils utils;

    /**
     * Test if the orientation is eligible to rotation. A orientation with a non-zero azimuth is eligible.
     *
     * @param orientation Orientation to test.
     * @return true if the orientation is eligible to a move.
     */
    private boolean doesItRotate(Point1S orientation) {
        if (orientation == null) return false;
        return orientation.getAzimuth() != 0;
    }

    private int compare(Point1S orientationA, Point1S orientationB) {
        var a = orientationA.getAzimuth();
        var b = orientationB.getAzimuth();
        return Double.compare(a, b);
    }

    /**
     * Test if the velocity is eligible to move. A velocity with a non-null azimuth and a non-zero speed is eligible.
     *
     * @param velocity Velocity to test.
     * @return true if the velocity is eligible to a move.
     */
    private boolean doesItWantToMove(Velocity velocity) {
        if (velocity == null) return false;
        if (velocity.azimuth() == null) return false;
        return velocity.speed() != 0;
    }

    /**
     * Add or update an {@link InGameObject in game objet} with the {@link Thing thing} at the {@link Vector2D coordinate}
     * and the {@link Velocity velocity}.
     *
     * @param thing      {@link Thing} to add in the {@link InGameObject in game objet}.
     * @param coordinate {@link Vector2D} where to put the {@link InGameObject in game objet}.
     * @param velocity   {@link Velocity} of the {@link InGameObject in game objet} in the world.
     */
    private void addOrUpdate(Thing thing, Vector2D coordinate, Point1S orientation, Velocity velocity) {
        var previous = inGameObjects.put(thing, new InGameObject(thing, coordinate, orientation, velocity));
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
    public void add(Thing thing, Vector2D coordinate, Point1S orientation) {
        var velocity = new Velocity(0, 0, Point1S.ZERO);
        addOrUpdate(thing, coordinate, orientation, velocity);
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
     * Move the {@link Thing thing} base on its current velocity.
     *
     * @param thing {@link Thing} witch to calculate the distance.
     */
    public void move(Thing thing) {
        var igo = inGameObjects.get(thing);
        move(thing, igo.orientation(), igo.velocity());
    }

    /**
     * Move the {@link Thing thing} to the destination relatively to the elapsed time since the last move and others
     * {@link Thing things}.
     *
     * @param thing             {@link Thing} witch to calculate the distance.
     * @param wantedOrientation Wanted rotation of the thing in radius;
     * @param wantedVelocity    Wanted {@link Velocity destination} by the {@link Thing thing}.
     */
    public void move(Thing thing, Point1S wantedOrientation, Velocity wantedVelocity) {
        var timestamps = new Date();
        var lastTimestamps = thingsLastMove.get(thing);
        thingsLastMove.put(thing, timestamps);

        var inGameObj = inGameObjects.get(thing);
        var newCoordinate = inGameObj.coordinate();
        var newOrientation = inGameObj.orientation();

        var hasItRotatedCompletely = true;
        if (doesItRotate(wantedOrientation)) {
            for (var other : inGameObjects.values()) {
                if (inGameObj != other) continue;

                var finalOrientation = utils.rotation(inGameObj, other, wantedOrientation);
                if (!wantedOrientation.equals(finalOrientation) && compare(finalOrientation, newOrientation) < 0) {
                    hasItRotatedCompletely = false;
                    newOrientation = finalOrientation;
                }
            }
        }
        var interval = (timestamps.getTime() - lastTimestamps.getTime()) / 1000.0;
        if (hasItRotatedCompletely && doesItWantToMove(wantedVelocity)) {
            var speed = inGameObj.velocity().speed();
            var p = physical.relativeCoordinates(interval, speed, wantedVelocity.azimuth());
            double distance = p.getRadius();
            for (var other : inGameObjects.values()) {
                var coordinate = utils.translation(inGameObj, other, p);
                var currentDistance = coordinate.getRadius();
                if (currentDistance < distance) {
                    distance = currentDistance;
                }
            }
            p = PolarCoordinates.of(distance, wantedVelocity.azimuth().getNormalizedAzimuth());
            newCoordinate = p.toCartesian();
        }
        var velocity = physical.recalculateVelocity(interval, inGameObj.velocity());
        addOrUpdate(inGameObj.thing(), newCoordinate, newOrientation, velocity);
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
        if (targetRelativeCoordinate.getRadius() > CATCHING_DISTANCE)
            return null;
        var catcher = this.get(lifeForm);
        var catcherCoordinate = catcher.coordinate();
        var targetCoordinate = catcherCoordinate;//.from(targetRelativeCoordinate);
        var target = (Food) this.getThing(targetCoordinate);
        return target;
    }

    /**
     * Calculate the relative polar coordinates between the two things.
     *
     * @param from Thing from where to calculate the polar coordinates.
     * @param to   Thing where to calculate the polar coordinates.
     * @return the relative polar coordinates in meters and radians.
     */
    public PolarCoordinates relativeCoordinates(Thing from, Thing to) {
        var fromObject = inGameObjects.get(from);
        var origin = PolarCoordinates.fromCartesian(fromObject.coordinate());
        var destination = PolarCoordinates.fromCartesian(inGameObjects.get(to).coordinate());

        var originAngle = fromObject.orientation();
        return PolarCoordinates.of(radius, azimuth);
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
