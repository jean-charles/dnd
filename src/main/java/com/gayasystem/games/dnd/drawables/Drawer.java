package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.ImageObserver;

import static java.lang.Math.max;

@Service
public class Drawer {
    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private SizeConvertor sizeConvertor;

    private boolean inside(Dimension size, Point p, int width, int depth) {
        if (p == null) return false;
        if (size == null) return false;
        int border = max(width / 2, depth / 2);
        if (p.x + border < 0) return false;
        if (p.x - border > size.width) return false;
        if (p.y + border < 0) return false;
        if (p.y - border > size.width) return false;
        return true;
    }

    /**
     * Draw in game object relatively to the player orientation. Player is always facing up.
     *
     * @param metersWidth Canvas width in meters.
     * @param size        Canvas {@link Dimension dimension}.
     * @param obj         {@link InGameObject} to draw in the Canvas.
     * @param center      {@link Vector2D Coordinates} of the center of the screen in game coordinates.
     * @param orientation Player orientation.
     * @param g           {@link Graphics} to use to draw the {@link InGameObject}.
     * @param observer    Object observer.
     */
    public void draw(double metersWidth, Dimension size, InGameObject obj, Vector2D center, Point1S orientation, Graphics g, ImageObserver observer) {
        try {
            String thingName = "drawable" + obj.thing().getClass().getSimpleName();
            Drawable drawable = (Drawable) ctx.getBean(thingName);

            int pixelsPerMeter = (int) (size.width / metersWidth);
            var p = sizeConvertor.coordinate2Point(pixelsPerMeter, size.width, size.height, center, obj.coordinate());
            var t = obj.thing();
            int width = sizeConvertor.meters2Pixels(pixelsPerMeter, t.width());
            int depth = sizeConvertor.meters2Pixels(pixelsPerMeter, t.depth());
            if (inside(size, p, width, depth))
                drawable.draw(pixelsPerMeter, obj, p, orientation, g, observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
