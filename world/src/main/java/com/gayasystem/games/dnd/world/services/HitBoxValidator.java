package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.services.domains.HitBox;
import org.apache.commons.geometry.euclidean.twod.Lines;
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

    private double shortestDistance(final HitBox from, final HitBox to, final double distance, final double azimuth) {
        var shortestDistance = distance;
        for (var hbPoint : from.points()) {
            var l = Lines.fromPointAndAngle(hbPoint, azimuth, p);
            for (var segment : to.segments(p)) {
                var intersection = segment.intersection(l);
                if (intersection != null) {
                    var currentDistance = hbPoint.distance(intersection);
                    if (currentDistance < shortestDistance)
                        shortestDistance = currentDistance;
                }
            }
        }
        return shortestDistance;
    }

    /**
     * Find the angle that the object can rotate until reach the other object or the desired angle.
     *
     * @param obj     Object to rotate.
     * @param other   Object tha can block the object to rotate.
     * @param azimuth Desired angle of rotation.
     * @return final angle of the rotation.
     */
    public Point1S rotation(final InGameObject obj, final InGameObject other, final Point1S azimuth) {
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
     * @param obj         Object to move.
     * @param other       Object that could block the movement.
     * @param coordinates Polar coordinates to use for the movement as destination.
     * @return the coordinate where the hit box are in contact or null if the other do not block.
     */
    public PolarCoordinates translation(final InGameObject obj, final InGameObject other, final PolarCoordinates coordinates) {
        var distance = coordinates.getRadius();
        var azimuth = coordinates.getAzimuth();
        var hb = utils.hitBox(obj);
        var hbOther = utils.hitBox(other);

        distance = shortestDistance(hb, hbOther, distance, azimuth);
        distance = shortestDistance(hbOther, hb, distance, azimuth);

        return PolarCoordinates.of(distance, azimuth);
    }
}
