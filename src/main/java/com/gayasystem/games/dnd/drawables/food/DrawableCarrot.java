package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.drawables.Drawable;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

@Service
public class DrawableCarrot implements Drawable {
    private final Image image;

    public DrawableCarrot() throws IOException {
        var img = ImageIO.read(this.getClass().getResource("/images/food/Carrot.png"));
        image = img.getScaledInstance(40, 60, Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(int width, int height, InGameObject obj, Point point, Graphics2D g) {
        var orientation = obj.orientation();
        int x = point.x;
        int y = point.y;

        g.drawImage(image, x - image.getWidth(null), y - image.getHeight(null), null);
    }
}
