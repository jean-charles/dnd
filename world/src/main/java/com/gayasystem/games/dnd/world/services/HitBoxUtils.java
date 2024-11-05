package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.services.domains.HitBox;
import com.gayasystem.games.dnd.world.services.domains.Point;
import org.apache.commons.geometry.euclidean.twod.Lines;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.euclidean.twod.shape.Circle;
import org.apache.commons.numbers.core.Precision;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.*;

@Service
public class HitBoxUtils {
    private final Precision.DoubleEquivalence p;

    public HitBoxUtils() {
        p = Precision.doubleEquivalenceOfEpsilon(1e-6);
    }

    public HitBox hitBox(InGameObject obj) {
        var c = obj.coordinate();
        var x = c.getX();
        var y = c.getY();
        var o = obj.velocity().destination().getAzimuth();
        var thing = obj.thing();
        var halfWidth = thing.width() / 2;
        var halfDepth = thing.depth() / 2;

        var p1 = rotateFrontLeft(x, y, o, halfWidth, halfDepth);
        var p2 = rotateFrontRight(x, y, o, halfWidth, halfDepth);
        var p3 = rotateRearRight(x, y, o, halfWidth, halfDepth);
        var p4 = rotateRearLeft(x, y, o, halfWidth, halfDepth);

        return new HitBox(c, p1, p2, p3, p4);
    }

    public Vector2D rotateFrontLeft(double x, double y, double phi, double halfWidth, double halfDepth) {
        var x1 = x + halfDepth;
        var y1 = y + halfWidth;
        return rotate(x, y, phi, x1, y1);
    }

    public Vector2D rotateFrontRight(double x, double y, double phi, double halfWidth, double halfDepth) {
        var x1 = x + halfDepth;
        var y1 = y - halfWidth;
        return rotate(x, y, phi, x1, y1);
    }

    public Vector2D rotateRearRight(double x, double y, double phi, double halfWidth, double halfDepth) {
        var x1 = x - halfDepth;
        var y1 = y - halfWidth;
        return rotate(x, y, phi, x1, y1);
    }

    public Vector2D rotateRearLeft(double x, double y, double phi, double halfWidth, double halfDepth) {
        var x1 = x - halfDepth;
        var y1 = y + halfWidth;
        return rotate(x, y, phi, x1, y1);
    }

    public Vector2D rotate(double xCenter, double yCenter, double phi, double xOrig, double yOrig) {
        var x = xOrig - xCenter;
        var y = yOrig - yCenter;
        double xDest = x * cos(phi) + y * sin(phi);
        double yDest = -x * sin(phi) + y * cos(phi);
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

    public double radius(HitBox hitBox) {
        var l = Lines.fromPoints(hitBox.center(), hitBox.p1(), p);
        var s = l.segment(hitBox.center(), hitBox.p1());
        return s.getSize();
    }

    public boolean couldItCrossIt(HitBox hb1, HitBox hb2) {
        var c = Circle.from(hb1.center(), radius(hb1), p);
        return Stream.of(
                Lines.fromPoints(hb2.p1(), hb2.p2(), p),
                Lines.fromPoints(hb2.p2(), hb2.p3(), p),
                Lines.fromPoints(hb2.p3(), hb2.p4(), p),
                Lines.fromPoints(hb2.p4(), hb2.p1(), p)
        ).anyMatch(line -> c.firstIntersection(line) != null);
    }

    public List<Vector2D> intersections(HitBox hb1, HitBox hb2) {
        var points = new ArrayList<Vector2D>();

        var c = Circle.from(hb1.center(), radius(hb1), p);
        points.addAll(c.intersections(Lines.fromPoints(hb2.p1(), hb2.p2(), p)));
        points.addAll(c.intersections(Lines.fromPoints(hb2.p2(), hb2.p3(), p)));
        points.addAll(c.intersections(Lines.fromPoints(hb2.p3(), hb2.p4(), p)));
        points.addAll(c.intersections(Lines.fromPoints(hb2.p4(), hb2.p1(), p)));

        return points;
    }
}
