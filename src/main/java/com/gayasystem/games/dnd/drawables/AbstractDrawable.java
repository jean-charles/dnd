package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.world.World;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.apache.commons.geometry.spherical.oned.Point1S;
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
    private World world;
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

    protected abstract int pixelsWidth(int pixelsPerMeter);

    protected abstract double widthOffset();

    protected abstract int pixelsDepth(int pixelsPerMeter);

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

    /**
     * Draw the current thing relatively to the player position and orientation.
     *
     * @param pixelsPerMeter
     * @param obj
     * @param point
     * @param up
     * @param g
     * @param observer
     */
    private void drawImage(int pixelsPerMeter, InGameObject obj, Point point, Point1S up, Graphics2D g, ImageObserver observer) {
        log.debug(format("Draw Image: %s", g.toString()));
        var thing = obj.thing();
        var d = new Data(pixelsPerMeter, point, thing.width(), thing.depth(), up.getAzimuth(), obj.orientation().getAzimuth());

        var image = image(obj).getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);
        var at = new AffineTransform();
        at.translate(d.x, d.y);
        at.rotate(d.rotation, (double) d.width / 2.0, (double) d.height / 2.0);

        g.drawImage(image, at, observer);
    }

    private void drawRepeatedImage(int pixelsPerMeter, InGameObject obj, Point point, Point1S up, Graphics2D g, ImageObserver observer) {
        var thing = obj.thing();
        var d = new Data(pixelsPerMeter, point, thing.width(), thing.depth(), up.getAzimuth(), obj.orientation().getAzimuth());

        var origImg = image(obj);
        var image = origImg.getScaledInstance(pixelsDepth(pixelsPerMeter), pixelsWidth(pixelsPerMeter), Image.SCALE_SMOOTH);
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        int nbX = 0;
        int nbY = 0;
        for (int offsetX = 0; offsetX < d.width; offsetX += imgWidth) {
            for (int offsetY = 0; offsetY < d.height; offsetY += imgHeight) {
                int drawImgWidth = min(d.width - imgWidth * nbX, imgWidth);
                int drawImgDepth = min(d.height - imgHeight * nbY, imgHeight);
                var cropped = cropImage(image, drawImgWidth, drawImgDepth);
                g.drawImage(cropped, offsetX + d.x, offsetY + d.y, drawImgWidth, drawImgDepth, null);
                nbY++;
            }
            nbX++;
        }
    }

    private void drawCollisionBorder(int pixelsPerMeter, InGameObject obj, Point point, Point1S up, Graphics2D g) {
        log.debug(format("Draw Border Collision: %s", g.toString()));
        var thing = obj.thing();
        var d = new Data(pixelsPerMeter, point, thing.width(), thing.depth(), up.getAzimuth(), obj.orientation().getAzimuth());

        g.setColor(red);
        g.drawRect(d.x, d.y, d.width, d.height);
        g.rotate(d.rotation, (double) d.width / 2.0, (double) d.height / 2.0);
    }

    @Override
    public void draw(int pixelsPerMeter, InGameObject obj, Point point, Point1S up, Graphics g, ImageObserver observer) {
        if (fill) drawRepeatedImage(pixelsPerMeter, obj, point, up, (Graphics2D) g, observer);
        else drawImage(pixelsPerMeter, obj, point, up, (Graphics2D) g, observer);
        drawCollisionBorder(pixelsPerMeter, obj, point, up, (Graphics2D) g);
    }

    private record Data(int x, int y, int width, int height, double rotation) {
        public Data(int pixelsPerMeter, Point p, double width, double height, double up, double orientation) {
            this(p, (int) (width * pixelsPerMeter), (int) (height * pixelsPerMeter), orientation - up);
        }

        public Data(Point p, int width, int height, double rotation) {
            this(p.x - (int) (width / 2), p.y - (int) (height / 2), width, height, rotation);
        }
    }
}
