package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.drawables.Drawable;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service
public class DrawableCarrot implements Drawable {
    private final BufferedImage img;

    @Autowired
    private MeasurementConvertor convertor;

    public DrawableCarrot() throws IOException {
        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/food/Carrot.png")));
    }

    @Override
    public void draw(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g) {
        var orientation = obj.orientation().phi().doubleValue();
        int x = point.x;
        int y = point.y;

        var at = new AffineTransform();
        int pixelsWidth = (int) (pixelsPerFoot * 1.6);
        int pixelsHeight = (int) (pixelsPerFoot * 1.4);
        var image = img.getScaledInstance(pixelsWidth, pixelsHeight, Image.SCALE_SMOOTH);
        at.translate(x - (double) image.getWidth(null) / 2, y - (double) image.getHeight(null) / 2);
        at.rotate(-orientation, (double) image.getWidth(null) / 2, (double) image.getHeight(null) / 2);
        ((Graphics2D) g).drawImage(image, at, null);
    }
}
