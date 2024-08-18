package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service
public class DrawableCarrot extends AbstractDrawable {
    private final BufferedImage img;
    private final double width;
    private final double height;

    public DrawableCarrot() throws IOException {
        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/food/Carrot.png")));
        double ratio = 0.15;
        width = 12 * ratio;
        height = 8 * ratio;
    }

    @Override
    protected BufferedImage image(InGameObject obj) {
        return img;
    }

    @Override
    protected int pixelsWidth(int pixelsPerFoot) {
        return (int) (pixelsPerFoot * width);
    }

    @Override
    protected int pixelsHeight(int pixelsPerFoot) {
        return (int) (pixelsPerFoot * height);
    }
}
