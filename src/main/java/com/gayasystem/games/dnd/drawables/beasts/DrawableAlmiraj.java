package com.gayasystem.games.dnd.drawables.beasts;

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
public class DrawableAlmiraj implements Drawable {
    private final BufferedImage img;

    @Autowired
    private MeasurementConvertor convertor;

    public DrawableAlmiraj() throws IOException {
        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/beasts/Almiraj.png")));
    }

    @Override
    public void draw(int pixelsPerFoot, InGameObject obj, Point point, Graphics2D g) {
        var orientation = obj.orientation().phi().doubleValue();
        int x = point.x;
        int y = point.y;

        var at = new AffineTransform();
        int pixels = (int) (pixelsPerFoot * 3.0);
        var image = img.getScaledInstance(pixels, pixels, Image.SCALE_SMOOTH);
        at.translate(x - (double) image.getWidth(null) / 2, y - (double) image.getHeight(null) / 2);
        at.rotate(-orientation, (double) image.getWidth(null) / 2, (double) image.getHeight(null) / 2);
        ((Graphics2D) g).drawImage(image, at, null);
    }
}
