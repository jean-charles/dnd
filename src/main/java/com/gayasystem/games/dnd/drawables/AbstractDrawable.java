package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.world.InGameObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static java.awt.Color.red;
import static java.lang.String.format;

public abstract class AbstractDrawable implements Drawable {
    private final Logger log;

    @Autowired
    private MeasurementConvertor convertor;

    protected AbstractDrawable(Logger log) {
        this.log = log;
    }

    protected abstract BufferedImage image(InGameObject obj);

    protected abstract int pixelsWidth(int pixelsPerFoot);

    protected abstract int pixelsWidthOffset(int pixelsPerFoot);

    protected abstract int pixelsHeight(int pixelsPerFoot);

    protected abstract int pixelsHeightOffset(int pixelsPerFoot);

    private void drawImage(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g, ImageObserver observer) {
        log.debug(format("Draw Image: %s", g.toString()));
        var orientation = -obj.orientation().phi().doubleValue();

        var image = image(obj).getScaledInstance(pixelsWidth(pixelsPerFoot), pixelsHeight(pixelsPerFoot), Image.SCALE_SMOOTH);
        int imgHalfWidth = image.getWidth(null) / 2;
        int imgHalfHeight = image.getHeight(null) / 2;
        int x = point.x - imgHalfWidth + pixelsWidthOffset(pixelsPerFoot);
        int y = point.y - imgHalfHeight + pixelsHeightOffset(pixelsPerFoot);

        var at = new AffineTransform();
        at.translate(x, y);
        at.rotate(orientation, imgHalfWidth, imgHalfHeight);
        g.drawImage(image, at, observer);
    }

    private void drawBorderCollision(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g) {
        log.debug(format("Draw Border Collision: %s", g.toString()));
        var thing = obj.thing();
        var orientation = obj.orientation().phi().doubleValue();
        int x = point.x;
        int y = point.y;
        int width = (int) (thing.width() * pixelsPerFoot);
        int depth = (int) (thing.depth() * pixelsPerFoot);

        g.setColor(red);
        var border = new Rectangle(x - depth / 2, y - width / 2, depth, width);
        g.rotate(-orientation, border.x + (double) border.width / 2, border.y + (double) border.height / 2);
        g.drawRect(border.x, border.y, border.width, border.height);
    }

    @Override
    public void draw(int pixelsPerFoot, InGameObject obj, Point point, Graphics g, ImageObserver observer) {
        drawImage(pixelsPerFoot, obj, point, (Graphics2D) g, observer);
        drawBorderCollision(pixelsPerFoot, obj, point, (Graphics2D) g);
    }
}
