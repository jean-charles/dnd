package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service
public class DrawableCarrot extends AbstractDrawable {
    private static final Logger log = LoggerFactory.getLogger(DrawableCarrot.class);

    private final BufferedImage img;
    private final double width;
    private final double height;

    public DrawableCarrot() throws IOException {
        super(log);
        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/food/Carrot.png")));
        double ratio = 0.10;
        width = 12 * ratio;
        height = 8 * ratio;
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
        return 0.0;
    }

    @Override
    protected int pixelsDepth(int pixelsPerMeter) {
        return 0;
    }

    @Override
    protected double depthOffset() {
        return 0.12;
    }
}
