package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.world.InGameObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static com.google.common.primitives.UnsignedInts.min;
import static java.awt.Color.red;
import static java.lang.String.format;

public abstract class AbstractDrawable implements Drawable {
    private final Logger log;

    private final boolean fill;

    @Autowired
    private MeasurementConvertor convertor;

    protected AbstractDrawable(Logger log, boolean fill) {
        this.log = log;
        this.fill = fill;
    }

    protected AbstractDrawable(Logger log) {
        this(log, false);
    }

    protected abstract BufferedImage image(InGameObject obj);

    protected abstract int pixelsWidth(int pixelsPerFoot);

    protected abstract double widthOffset();

    protected abstract int pixelsDepth(int pixelsPerFoot);

    protected abstract double depthOffset();

    private BufferedImage cropImage(Image image, int drawImgWidth, int drawImgDepth) throws IllegalArgumentException {
        // Image default position is to look forward to the right of the screen.
        // So image height is object width and image width is object depth
        int width = image.getHeight(null);
        int height = image.getWidth(null);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Image dimensions are invalid");
        }
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bi.getGraphics().drawImage(image, 0, 0, null);

        var croppedImg = bi.getSubimage(0, 0, drawImgWidth, drawImgDepth);
        return croppedImg;
    }

    private void drawImage(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g, ImageObserver observer) {
        log.debug(format("Draw Image: %s", g.toString()));
        // Image default position is to look forward to the right of the screen.
        // So image height is object width and image width is object depth
        var thing = obj.thing();
        int width = (int) (thing.depth() * pixelsPerFoot);
        int height = (int) (thing.width() * pixelsPerFoot);
        int x = point.x - width / 2 ;
        int y = point.y - height / 2;

        var orientation = -obj.orientation().phi().doubleValue();

        var image = image(obj).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        var at = new AffineTransform();
        at.translate(x, y);
        at.rotate(orientation, (double) width / 2, (double) height / 2);

        g.drawImage(image, at, observer);
    }

    private void drawRepeatedImage(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g, ImageObserver observer) {
        // Image default position is to look forward to the right of the screen.
        // So image height is object width and image width is object depth
        var thing = obj.thing();
        int width = (int) (thing.depth() * pixelsPerFoot);
        int height = (int) (thing.width() * pixelsPerFoot);
        int x = point.x - width / 2;
        int y = point.y - height / 2;

        var orientation = -obj.orientation().phi().doubleValue();

        var origImg = image(obj);
        var image = origImg.getScaledInstance(pixelsDepth(pixelsPerFoot), pixelsWidth(pixelsPerFoot), Image.SCALE_SMOOTH);
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        int nbX = 0;
        int nbY = 0;
        for (int offsetX = 0; offsetX < width; offsetX += imgWidth) {
            for (int offsetY = 0; offsetY < height; offsetY += imgHeight) {
                int drawImgWidth = min(width - imgWidth * nbX, imgWidth);
                int drawImgDepth = min(height - imgHeight * nbY, imgHeight);
                var cropped = cropImage(image, drawImgWidth, drawImgDepth);
                g.drawImage(cropped, offsetX + x, offsetY + y, drawImgWidth, drawImgDepth, null);
                nbY++;
            }
            nbX++;
        }
    }

    private void drawCollisionBorder(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g) {
        log.debug(format("Draw Border Collision: %s", g.toString()));
        // Image default position is to look forward to the right of the screen.
        // So image height is object width and image width is object depth
        var thing = obj.thing();
        int width = (int) (thing.depth() * pixelsPerFoot);
        int height = (int) (thing.width() * pixelsPerFoot);
        int x = point.x - width / 2 ;
        int y = point.y - height / 2;

        var orientation = -obj.orientation().phi().doubleValue();

        g.setColor(red);
        g.drawRect(x, y, width, height);
        g.rotate(orientation, (double) width / 2, (double) height / 2);
    }

    @Override
    public void draw(int pixelsPerFoot, InGameObject obj, Point point, Graphics g, ImageObserver observer) {
        if (fill)
            drawRepeatedImage(pixelsPerFoot, obj, point, (Graphics2D) g, observer);
        else
            drawImage(pixelsPerFoot, obj, point, (Graphics2D) g, observer);
        drawCollisionBorder(pixelsPerFoot, obj, point, (Graphics2D) g);
    }
}
