package com.gayasystem.games.dnd.drawables.beasts;

import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service
public class DrawableAlmiraj extends AbstractDrawable {
    private final BufferedImage img;
    private final int width;
    private final double heightRatio;

    public DrawableAlmiraj() throws IOException {
        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/beasts/Almiraj.png")));
        width = 3;
        heightRatio = 0.75;
    }

    @Override
    protected BufferedImage image(InGameObject obj) {
        return img;
    }

    @Override
    protected int pixelsWidth(int pixelsPerFoot) {
        return pixelsPerFoot * width;
    }

    @Override
    protected int pixelsHeight(int pixelsPerFoot) {
        return (int) (pixelsPerFoot * width * heightRatio);
    }
}
