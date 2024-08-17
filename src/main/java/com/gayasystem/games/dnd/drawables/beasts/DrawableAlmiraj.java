package com.gayasystem.games.dnd.drawables.beasts;

import com.gayasystem.games.dnd.drawables.Drawable;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Objects;

@Service
public class DrawableAlmiraj implements Drawable {
    private final Image image;

    public DrawableAlmiraj() throws IOException {
        var img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/beasts/Almiraj.png")));
        image = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(int width, int height, InGameObject obj, Point point, Graphics2D g) {
        var orientation = obj.orientation().phi().doubleValue();
        int x = point.x;
        int y = point.y;

        var at = new AffineTransform();
        at.translate(x - (double) image.getWidth(null) / 2, y - (double) image.getHeight(null) / 2);
        at.rotate(-orientation, (double) image.getWidth(null) / 2, (double) image.getHeight(null) / 2);
        ((Graphics2D) g).drawImage(image, at, null);
    }
}
