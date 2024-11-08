package com.gayasystem.games.dnd.drawables.houses;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.world.InGameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Service
public class DrawableWall extends AbstractDrawable {
    private static final Logger log = LoggerFactory.getLogger(DrawableWall.class);

    private final BufferedImage image;
    private final double size;

    @Autowired
    private MeasurementConvertor convertor;

    public DrawableWall() throws IOException {
        super(log, true);
        image = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/houses/Wall.jpeg")));
        size = 4;
    }

    @Override
    protected BufferedImage image(InGameObject obj) {
        return image;
    }

    @Override
    protected int pixelsWidth(int pixelsPerMeter) {
        return (int) (pixelsPerMeter * size);
    }

    @Override
    protected double widthOffset() {
        return 0;
    }

    @Override
    protected int pixelsDepth(int pixelsPerMeter) {
        return (int) (pixelsPerMeter * size);
    }

    @Override
    protected double depthOffset() {
        return 0;
    }
}
