package com.gayasystem.games.dnd.drawables.beasts;

import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.drawables.food.DrawableCarrot;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service
public class DrawableAlmiraj extends AbstractDrawable {
    private static final Logger log = LoggerFactory.getLogger(DrawableCarrot.class);

    private final BufferedImage img;

    public DrawableAlmiraj() throws IOException {
        super(log);
        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/beasts/Almiraj.png")));
    }

    @Override
    protected BufferedImage image(InGameObject obj) {
        return img;
    }

    @Override
    protected int pixelsWidth(int pixelsPerMeter) {
        return 0;
    }

    @Override
    protected double widthOffset() {
        return 0;
    }

    @Override
    protected int pixelsDepth(int pixelsPerMeter) {
        return 0;
    }

    @Override
    protected double depthOffset() {
        return 0;
    }
}
