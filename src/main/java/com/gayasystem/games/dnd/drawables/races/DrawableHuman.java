package com.gayasystem.games.dnd.drawables.races;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.drawables.AbstractDrawable;
import com.gayasystem.games.dnd.ecosystem.races.Human;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
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
        this(log);
    }

    public DrawableHuman(Logger logger) throws IOException {
        super(logger);
        imgFemale = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanFemale.png")));
        imgMale = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanMale.png")));
        size = 0.8 * 388 / 150;
    }

    @Override
    protected BufferedImage image(InGameObject obj) {
        var gender = ((Human) obj.thing()).gender();
        return gender == female ? imgFemale : imgMale;
    }

    @Override
    protected double width() {
        return size;
    }

    @Override
    protected int widthOffset() {
        return 150;
    }

    @Override
    protected double depth() {
        return size;
    }

    @Override
    protected int depthOffset() {
        return 180;
    }
}
