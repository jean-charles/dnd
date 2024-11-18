package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
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
    private final double depth;

    public DrawableCarrot() throws IOException {
        super(log);
        img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/food/Carrot.png")));
        width = Carrot.WIDTH * img.getWidth() / 230; // width of the carrot in pixels in the image
        depth = Carrot.DEPTH * img.getHeight() / 850; // depth of the carrot in pixels in the image
    }

    @Override
    protected BufferedImage image(InGameObject obj) {
        return img;
    }

    @Override
    protected double width() {
        return width;
    }

    @Override
    protected int widthOffset() {
        return 310;
    }

    @Override
    protected double depth() {
        return depth;
    }

    @Override
    protected int depthOffset() {
        return 10;
    }
}
