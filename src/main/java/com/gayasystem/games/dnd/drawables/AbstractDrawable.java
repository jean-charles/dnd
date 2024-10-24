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

    protected abstract int pixelsWidthOffset(int pixelsPerFoot);

    protected abstract int pixelsDepth(int pixelsPerFoot);

    protected abstract int pixelsDepthOffset(int pixelsPerFoot);

    private BufferedImage cropImage(Image image, int drawImgWidth, int drawImgDepth) throws IllegalArgumentException {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
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
        var orientation = -obj.orientation().phi().doubleValue();

        var image = image(obj).getScaledInstance(pixelsWidth(pixelsPerFoot), pixelsDepth(pixelsPerFoot), Image.SCALE_SMOOTH);
        int imgHalfWidth = image.getWidth(null) / 2;
        int imgHalfDepth = image.getHeight(null) / 2;
        int x = point.x - imgHalfWidth + pixelsWidthOffset(pixelsPerFoot);
        int y = point.y - imgHalfDepth + pixelsDepthOffset(pixelsPerFoot);

        var at = new AffineTransform();
        at.translate(x, y);
        at.rotate(orientation, imgHalfWidth, imgHalfDepth);
        g.drawImage(image, at, observer);
    }

    private void drawRepeatedImage(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g, ImageObserver observer) {
        var image = image(obj).getScaledInstance(pixelsWidth(pixelsPerFoot), pixelsDepth(pixelsPerFoot), Image.SCALE_SMOOTH);
        var thing = obj.thing();
        int width = (int) (thing.width() * pixelsPerFoot);
        int depth = (int) (thing.depth() * pixelsPerFoot);
        int x = point.x - width / 2;
        int y = point.y - depth / 2;
        int imgWidth = image.getWidth(null);
        int imgDepth = image.getHeight(null);

        int nbX = 0;
        int nbY = 0;
        for (int offsetX = 0; offsetX < width; offsetX += imgWidth) {
            for (int offsetY = 0; offsetY < depth; offsetY += imgDepth) {
                int drawImgWidth = min(width - imgWidth * nbX, imgWidth);
                int drawImgDepth = min(depth - imgDepth * nbY, imgDepth);
                var cropped = cropImage(image, drawImgWidth, drawImgDepth);
                g.drawImage(cropped, offsetX + x, offsetY + y, drawImgWidth, drawImgDepth, null);
                nbY++;
            }
            nbX++;
        }
    }

    private void drawCollisionBorder(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g) {
        log.debug(format("Draw Border Collision: %s", g.toString()));
        var thing = obj.thing();
        int x = point.x;
        int y = point.y;
        int width = (int) (thing.width() * pixelsPerFoot);
        int depth = (int) (thing.depth() * pixelsPerFoot);

        g.setColor(red);
        var border = new Rectangle(x - width / 2, y - depth / 2, width, depth);
        var orientation = obj.orientation().phi().doubleValue();
        g.rotate(-orientation, border.x + (double) border.width / 2, border.y + (double) border.height / 2);
        g.drawRect(border.x, border.y, border.width, border.height);
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
