package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.world.services.domains.HitBox;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.shape.Circle;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.numbers.core.Precision;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static java.lang.Math.*;

@Service
public class HitBoxUtils {
    private final Precision.DoubleEquivalence p;

    public HitBoxUtils() {
        p = Precision.doubleEquivalenceOfEpsilon(1e-6);
    }

    private Circle circle(Vector2D center, Vector2D point) {
        var radius = center.distance(point);
        return Circle.from(center, radius, p);
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

    public HitBox hitBox(InGameObject obj) {
        var c = obj.coordinate();
        var x = c.getX();
        var y = c.getY();
        var o = obj.velocity().azimuth();
        var thing = obj.thing();
        var halfWidth = thing.width() / 2;
        var halfDepth = thing.depth() / 2;

        var p1 = rotateFrontLeft(x, y, o, halfWidth, halfDepth);
        var p2 = rotateFrontRight(x, y, o, halfWidth, halfDepth);
        var p3 = rotateRearRight(x, y, o, halfWidth, halfDepth);
        var p4 = rotateRearLeft(x, y, o, halfWidth, halfDepth);

        return new HitBox(c, p1, p2, p3, p4);
    }

    public Vector2D rotateFrontLeft(double x, double y, Point1S orientation, double halfWidth, double halfDepth) {
        var x1 = x + halfDepth;
        var y1 = y + halfWidth;
        return rotate(x, y, orientation, x1, y1);
    }

    public Vector2D rotateFrontRight(double x, double y, Point1S orientation, double halfWidth, double halfDepth) {
        var x1 = x + halfDepth;
        var y1 = y - halfWidth;
        return rotate(x, y, orientation, x1, y1);
    }

    public Vector2D rotateRearRight(double x, double y, Point1S orientation, double halfWidth, double halfDepth) {
        var x1 = x - halfDepth;
        var y1 = y - halfWidth;
        return rotate(x, y, orientation, x1, y1);
    }

    public Vector2D rotateRearLeft(double x, double y, Point1S orientation, double halfWidth, double halfDepth) {
        var x1 = x - halfDepth;
        var y1 = y + halfWidth;
        return rotate(x, y, orientation, x1, y1);
    }

    public Vector2D rotate(double xCenter, double yCenter, Point1S orientation, double xOrig, double yOrig) {
        var x = xOrig - xCenter;
        var y = yOrig - yCenter;
        double xDest = x * cos(orientation.getAzimuth()) + y * sin(orientation.getAzimuth());
        double yDest = -x * sin(orientation.getAzimuth()) + y * cos(orientation.getAzimuth());
        xDest += xCenter;
        yDest += yCenter;

        return Vector2D.of(xDest, yDest);
    }

    public double minX(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4) {
        return min(min(min(p1.getX(), p2.getX()), p3.getX()), p4.getX());
    }

    public double maxX(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4) {
        return max(max(max(p1.getX(), p2.getX()), p3.getX()), p4.getX());
    }

    public double minY(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4) {
        return min(min(min(p1.getY(), p2.getY()), p3.getY()), p4.getY());
    }

    public double maxY(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4) {
        return max(max(max(p1.getY(), p2.getY()), p3.getY()), p4.getY());
    }


    public boolean couldItCrossIt(HitBox hb1, HitBox hb2) {
        var cOrig = circle(hb1.center(), hb1.p1());
        var c1 = circle(hb2.center(), hb1.p1());
        var c2 = circle(hb2.center(), hb2.p2());
        var c3 = circle(hb2.center(), hb2.p3());
        var c4 = circle(hb2.center(), hb2.p4());
        return Stream.of(c1, c2, c3, c4).anyMatch(c -> c.getRadius() < cOrig.getRadius());
    }

    public double azimuthWhereItCross(HitBox hb1, HitBox hb2, double azimuth) {
        var a1 = hb2.center().angle(hb1.p1());
        var a2 = hb2.center().angle(hb2.p2());
        var a3 = hb2.center().angle(hb2.p3());
        var a4 = hb2.center().angle(hb2.p4());

        return Stream.of(azimuth, a1, a2, a3, a4).min(Double::compare).get();
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
        var objHb = hitBox(obj);
        var otherHb = hitBox(other);

        if (!couldItCrossIt(objHb, otherHb)) {
            return Point1S.of(azimuthWhereItCross(objHb, otherHb, azimuth.getAzimuth()));
        }

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
        var hb = hitBox(obj);
        var hbOther = hitBox(other);

        distance = shortestDistance(hb, hbOther, distance, azimuth);
        distance = shortestDistance(hbOther, hb, distance, azimuth);

        return PolarCoordinates.of(distance, azimuth);
    }
}
