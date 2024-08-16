package com.gayasystem.games.dnd.drawables.races;

import com.gayasystem.games.dnd.drawables.Drawable;
import com.gayasystem.games.dnd.ecosystem.races.Human;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Objects;

import static com.gayasystem.games.dnd.lifeforms.Gender.female;

@Service
public class DrawableHuman implements Drawable {
    private final Image imageFemale;
    private final Image imageMale;

    public DrawableHuman() throws IOException {
        var img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanFemale.png")));
        imageFemale = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanMale.png")));
        imageMale = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(int width, int height, InGameObject obj, Point point, Graphics2D g) {
        var orientation = obj.orientation().phi().doubleValue();
        int x = point.x;
        int y = point.y;

        var at = new AffineTransform();
        Image image = ((Human) obj.thing()).gender() == female ? imageFemale : imageMale;
        at.translate(x - (double) image.getWidth(null) / 2, y - (double) image.getHeight(null) / 2);
        at.rotate(orientation, (double) image.getWidth(null) / 2, (double) image.getHeight(null) / 2);
        ((Graphics2D) g).drawImage(image, at, null);
    }
}
