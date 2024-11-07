package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.world.InGameObject;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.numbers.core.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HitBoxValidator {
    private final Precision.DoubleEquivalence p;

    @Autowired
    private HitBoxUtils utils;

    public HitBoxValidator() {
        this.p = Precision.doubleEquivalenceOfEpsilon(1e-6);
    }

    /**
     * Find the angle that the object can rotate until reach the other object or the desired angle.
     *
     * @param obj     Object to rotate.
     * @param other   Object tha can block the object to rotate.
     * @param azimuth Desired angle of rotation.
     * @return final angle of the rotation.
     */
    public Point1S rotation(InGameObject obj, InGameObject other, Point1S azimuth) {
        var objHb = utils.hitBox(obj);
        var otherHb = utils.hitBox(other);

        if (!utils.couldItCrossIt(objHb, otherHb)) {
            return azimuth;
        }
        var points = utils.intersections(objHb, otherHb);

        return azimuth;
    }

    /**
     * Move the object until it's blocked by the other object.
     *
     * @param obj      Object to move.
     * @param other    Object that could block the movement.
     * @param velocity Velocity to use for the movement.
     * @return the coordinate where the hit box are in contact or null if the other do not block.
     */
    public PolarCoordinates translate(InGameObject obj, InGameObject other, Velocity velocity, double interval) {
        var distance = velocity.speed() * interval;
        var hb = utils.hitBox(obj);
        var hbOther = utils.hitBox(other);


        return PolarCoordinates.of(distance, velocity.azimuth().getNormalizedAzimuth());
    }
}
