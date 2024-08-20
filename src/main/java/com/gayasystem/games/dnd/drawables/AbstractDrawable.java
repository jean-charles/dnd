package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static java.awt.Color.red;

public abstract class AbstractDrawable implements Drawable {
    @Autowired
    private MeasurementConvertor convertor;

    protected abstract BufferedImage image(InGameObject obj);

    protected abstract int pixelsWidth(int pixelsPerFoot);

    protected abstract int pixelsHeight(int pixelsPerFoot);

    private void drawImage(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g, ImageObserver observer) {
        var orientation = obj.orientation().phi().doubleValue();
        int x = point.x;
        int y = point.y;

        var image = image(obj).getScaledInstance(pixelsWidth(pixelsPerFoot), pixelsHeight(pixelsPerFoot), Image.SCALE_SMOOTH);
        var at = new AffineTransform();
        at.translate(x - (double) image.getWidth(null) / 2, y - (double) image.getHeight(null) / 2);
        at.rotate(-orientation, (double) image.getWidth(null) / 2, (double) image.getHeight(null) / 2);
        g.drawImage(image, at, observer);
    }

    private void drawBorderCollision(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g) {
        var thing = obj.thing();
        var orientation = obj.orientation().phi().doubleValue();
        int x = point.x;
        int y = point.y;
        int width = (int) (thing.width() * pixelsPerFoot);
        int height = (int) (thing.depth() * pixelsPerFoot);

        g.setColor(red);
        var border = new Rectangle(x - width / 2, y - height / 2, width, height);
        g.rotate(-orientation);
        g.draw(border);
    }

    @Override
    public void draw(int pixelsPerFoot, InGameObject obj, Point point, Graphics g, ImageObserver observer) {
        Graphics2D g1 = (Graphics2D) g.create();
        drawImage(pixelsPerFoot, obj, point, g1, observer);
        g1.dispose();
        Graphics2D g2 = (Graphics2D) g.create();
        drawBorderCollision(pixelsPerFoot, obj, point, g2);
        g2.dispose();
    }
}
