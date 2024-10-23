package com.gayasystem.games.dnd.drawables.races;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.ecosystem.races.Human;
import com.gayasystem.games.dnd.world.InGameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static com.gayasystem.games.dnd.lifeforms.Gender.female;

@Service
public class DrawableHuman extends AbstractDrawable {
    private static final Logger log = LoggerFactory.getLogger(DrawableHuman.class);

    private final BufferedImage imgFemale;
    private final BufferedImage imgMale;
    private final double size;

    @Autowired
    private MeasurementConvertor convertor;

    public DrawableHuman() throws IOException {
        super(log);
        imgFemale = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanFemale.png")));
        imgMale = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanMale.png")));
        size = 2.8;
    }

    @Override
    protected BufferedImage image(InGameObject obj) {
        var gender = ((Human) obj.thing()).gender();
        return gender == female ? imgFemale : imgMale;
    }

    @Override
    protected int pixelsWidth(int pixelsPerFoot) {
        return (int) (pixelsPerFoot * size);
    }

    @Override
    protected int pixelsWidthOffset(int pixelsPerFoot) {
        return (int) (0.43 * pixelsPerFoot);
    }

    @Override
    protected int pixelsDepth(int pixelsPerFoot) {
        return (int) (pixelsPerFoot * size);
    }

    @Override
    protected int pixelsDepthOffset(int pixelsPerFoot) {
        return (int) (0.2 * pixelsPerFoot);
    }
}
