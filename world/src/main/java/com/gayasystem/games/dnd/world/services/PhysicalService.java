package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Velocity;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.geometry.spherical.oned.Transform1S;
import org.springframework.stereotype.Service;

@Service
public class PhysicalService {
    private static final double AIR_RESISTANCE = 0.1;

    /**
     * Recalculate the new speed and the deceleration base on air resistance.
     *
     * @param interval Interval between last change in second.
     * @param velocity Velocity to recalculate.
     * @return new velocity.
     */
    public Velocity recalculateVelocity(double interval, Velocity velocity) {
        var speed = velocity.speed();
        var acceleration = velocity.acceleration();
        var azimuth = velocity.azimuth();

        var newSpeed = slowingDown(interval, speed, acceleration);
        var newAcceleration = slowingDown(interval, acceleration);

        return new Velocity(newSpeed, newAcceleration, azimuth);
    }

    private double distance(double interval, double speed) {
        return speed * interval;
    }

    private double slowingDown(double interval, double speed, double acceleration) {
        return distance(speed, interval) * acceleration;
    }

    private double slowingDown(double interval, double acceleration) {
        return acceleration - AIR_RESISTANCE;
    }

    public Point1S rotate(Point1S p, double angle) {
        var t = Transform1S.createRotation(angle);
        var newPoint = t.apply(p);
        var normalizedAzimuth = newPoint.getNormalizedAzimuth();
        return Point1S.of(normalizedAzimuth);
    }

    private Vector2D move(Vector2D coordinate, PolarCoordinates direction) {
        return coordinate.add(direction.toCartesian());
    }

    public PolarCoordinates relativeCoordinates(double interval, double speed, Point1S orientation) {
        return PolarCoordinates.of(distance(interval, speed), orientation.getNormalizedAzimuth());
    }
}
