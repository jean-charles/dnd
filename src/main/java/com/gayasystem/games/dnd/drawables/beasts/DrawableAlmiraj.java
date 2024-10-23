package com.gayasystem.games.dnd.drawables.beasts;

import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.world.InGameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service
public class DrawableAlmiraj extends AbstractDrawable {
    private static final Logger log = LoggerFactory.getLogger(DrawableAlmiraj.class);

    private final BufferedImage img;
    private final double width;
    private final double heightRatio;

    public DrawableAlmiraj() throws IOException {
        super(log);
        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/beasts/Almiraj.png")));
        width = 1.9;
        heightRatio = 0.75;
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
    protected int pixelsWidthOffset(int pixelsPerFoot) {
        return 0;
    }

    @Override
    protected int pixelsDepth(int pixelsPerFoot) {
        return (int) (pixelsPerFoot * width * heightRatio);
    }

    @Override
    protected int pixelsDepthOffset(int pixelsPerFoot) {
        return 0;
    }
}
