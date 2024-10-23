package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.services.domains.AlignedRectangle;
import com.gayasystem.games.dnd.world.services.domains.Corner;
import com.gayasystem.games.dnd.world.services.domains.HitBox;
import com.gayasystem.games.dnd.world.services.domains.Point;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.gayasystem.games.dnd.world.services.domains.Corner.*;
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
        var o = obj.orientation();
        var thing = obj.thing();
        var width = thing.width();
        var depth = thing.depth();

        var p1 = rotate(topRight, c, o, width, depth);
        var p2 = rotate(bottomRight, c, o, width, depth);
        var p3 = rotate(bottomLeft, c, o, width, depth);
        var p4 = rotate(topLeft, c, o, width, depth);

        return new HitBox(p1, p2, p3, p4);
    }

    public Point rotate(Corner corner, Coordinate c, Orientation o, double width, double depth) {
        switch (corner) {
            case topLeft -> {
                var x1 = c.x().subtract(BigDecimal.valueOf(depth / 2));
                var y1 = c.y().add(BigDecimal.valueOf(width / 2));
                return rotate(c, o, x1, y1);
            }
            case topRight -> {
                var x1 = c.x().add(BigDecimal.valueOf(depth / 2));
                var y1 = c.y().add(BigDecimal.valueOf(width / 2));
                return rotate(c, o, x1, y1);
            }
            case bottomLeft -> {
                var x1 = c.x().subtract(BigDecimal.valueOf(depth / 2));
                var y1 = c.y().subtract(BigDecimal.valueOf(width / 2));
                return rotate(c, o, x1, y1);
            }
            case bottomRight -> {
                var x1 = c.x().add(BigDecimal.valueOf(depth / 2));
                var y1 = c.y().subtract(BigDecimal.valueOf(width / 2));
                return rotate(c, o, x1, y1);
            }
        }
        return null;
    }

    public Point rotate(Coordinate c, Orientation o, BigDecimal x, BigDecimal y) {
        var tmpX = x.subtract(c.x());
        var tmpY = y.subtract(c.y());

        var hyp = sqrt(tmpX.multiply(tmpX).add(tmpY.multiply(tmpY)).doubleValue());

        var angleCos = acos(tmpX.doubleValue() / hyp);
        var angleSin = asin(tmpY.doubleValue() / hyp);
        var angle = angleCos < angleSin ? angleCos : angleSin;
        var newAngle = angle + o.phi().doubleValue();

        var newX = hyp * cos(newAngle);
        var newY = hyp * sin(newAngle);

        return new Point(c.x().add(BigDecimal.valueOf(newX)), c.y().add(BigDecimal.valueOf(newY)));
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
