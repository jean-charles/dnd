package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.services.domains.AlignedRectangle;
import com.gayasystem.games.dnd.world.services.domains.HitBox;
import com.gayasystem.games.dnd.world.services.domains.Point;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.lang.Math.*;

@Service
public class HitBoxUtils {
    public AlignedRectangle alignedRectangle(InGameObject obj) {
        var hb = hitBox(obj);
        var p1 = new Point(minX(hb.p1(), hb.p2(), hb.p3(), hb.p4()), minY(hb.p1(), hb.p2(), hb.p3(), hb.p4()));
        var p2 = new Point(maxX(hb.p1(), hb.p2(), hb.p3(), hb.p4()), maxY(hb.p1(), hb.p2(), hb.p3(), hb.p4()));
        return new AlignedRectangle(p1, p2);
    }

    public HitBox hitBox(InGameObject obj) {
        var c = obj.coordinate();
        var x = c.x().doubleValue();
        var y = c.y().doubleValue();
        var o = obj.velocity().destination().orientation().phi().doubleValue();
        var thing = obj.thing();
        var halfWidth = thing.width() / 2;
        var halfDepth = thing.depth() / 2;

        var p1 = rotateFrontLeft(x, y, o, halfWidth, halfDepth);
        var p2 = rotateFrontRight(x, y, o, halfWidth, halfDepth);
        var p3 = rotateRearRight(x, y, o, halfWidth, halfDepth);
        var p4 = rotateRearLeft(x, y, o, halfWidth, halfDepth);

        return new HitBox(p1, p2, p3, p4);
    }

    public Point rotateFrontLeft(double x, double y, double phi, double halfWidth, double halfDepth) {
        var x1 = x + halfDepth;
        var y1 = y + halfWidth;
        return rotate(x, y, phi, x1, y1);
    }

    public Point rotateFrontRight(double x, double y, double phi, double halfWidth, double halfDepth) {
        var x1 = x + halfDepth;
        var y1 = y - halfWidth;
        return rotate(x, y, phi, x1, y1);
    }

    public Point rotateRearRight(double x, double y, double phi, double halfWidth, double halfDepth) {
        var x1 = x - halfDepth;
        var y1 = y - halfWidth;
        return rotate(x, y, phi, x1, y1);
    }

    public Point rotateRearLeft(double x, double y, double phi, double halfWidth, double halfDepth) {
        var x1 = x - halfDepth;
        var y1 = y + halfWidth;
        return rotate(x, y, phi, x1, y1);
    }

    public Point rotate(double xCenter, double yCenter, double phi, double xOrig, double yOrig) {
        var x = xOrig - xCenter;
        var y = yOrig - yCenter;
        double xDest = x * cos(phi) + y * sin(phi);
        double yDest = -x * sin(phi) + y * cos(phi);
        xDest += xCenter;
        yDest += yCenter;

        return new Point(BigDecimal.valueOf(xDest), BigDecimal.valueOf(yDest));
    }

    public BigDecimal minX(Point p1, Point p2, Point p3, Point p4) {
        return p1.x().min(p2.x()).min(p3.x()).min(p4.x());
    }

    public BigDecimal maxX(Point p1, Point p2, Point p3, Point p4) {
        return p1.x().max(p2.x()).max(p3.x()).max(p4.x());
    }

    public BigDecimal minY(Point p1, Point p2, Point p3, Point p4) {
        return p1.y().min(p2.y()).min(p3.y()).min(p4.y());
    }

    public BigDecimal maxY(Point p1, Point p2, Point p3, Point p4) {
        return p1.y().max(p2.y()).max(p3.y()).max(p4.y());
    }
}
