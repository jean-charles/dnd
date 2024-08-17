package com.gayasystem.games.dnd.drawables.races;

import com.gayasystem.games.dnd.drawables.Drawable;
import com.gayasystem.games.dnd.drawables.SizeConvertor;
import com.gayasystem.games.dnd.ecosystem.races.Human;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static com.gayasystem.games.dnd.lifeforms.Gender.female;

@Service
public class DrawableHuman implements Drawable {
    private final BufferedImage imgFemale;
    private final BufferedImage imgMale;

    @Autowired
    private SizeConvertor convertor;

    public DrawableHuman() throws IOException {
        imgFemale = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanFemale.png")));
        imgMale = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanMale.png")));
    }

    @Override
    public void draw(int feetWidth, int width, int height, InGameObject obj, Point point, Graphics2D g) {
        var orientation = obj.orientation().phi().doubleValue();
        int x = point.x;
        int y = point.y;

        var at = new AffineTransform();
        Image image;
        var pixels = convertor.feet2Pixels(feetWidth, width, 5);
        if (((Human) obj.thing()).gender() == female)
            image = imgFemale.getScaledInstance(pixels, pixels, Image.SCALE_SMOOTH);
        else
            image = imgMale.getScaledInstance(pixels, pixels, Image.SCALE_SMOOTH);
        at.translate(x - (double) image.getWidth(null) / 2, y - (double) image.getHeight(null) / 2);
        at.rotate(-orientation, (double) image.getWidth(null) / 2, (double) image.getHeight(null) / 2);
        ((Graphics2D) g).drawImage(image, at, null);
    }
}
