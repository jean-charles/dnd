package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.SwingTestCase;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.drawables.SizeConvertor;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.world.World;
import com.gayasystem.games.dnd.world.services.HitBoxUtils;
import com.gayasystem.games.dnd.world.services.InGameObjectsManager;
import com.gayasystem.games.dnd.world.services.PhysicalService;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.awt.Color.black;
import static java.lang.Math.PI;
import static java.lang.Thread.sleep;

@SpringBootTest(classes = {PhysicalService.class, HitBoxUtils.class, World.class, InGameObjectsManager.class, DrawableCarrot.class, SizeConvertor.class, MeasurementConvertor.class})
class DrawableCarrotTest extends SwingTestCase {
    private JFrame frame;
    private JPanel panel;
    private DrawableCarrot drawableCarrot;

    private InGameObject carrotObj;

    @Autowired
    private MeasurementConvertor convertor;

    @Autowired
    private ApplicationContext ctx;

    @BeforeEach
    public void setUp() throws IOException {
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Point p = new Point(getWidth() / 2, getHeight() / 2);
                int pixelsPerMeter = (int) (getWidth() / 0.5);

                Graphics2D g2d = (Graphics2D) g;
                if (carrotObj != null)
                    drawableCarrot.draw(pixelsPerMeter, carrotObj, p, Point1S.ZERO, g2d, null);

            }
        };
        panel.setSize(800, 800);
        panel.setBackground(black);
        panel.setDoubleBuffered(true);
        drawableCarrot = ctx.getBean(DrawableCarrot.class);
        frame = getTestFrame();
        frame.setSize(800, 800);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Test
    public void rotate() throws InterruptedException {
        double max = 2 * PI;
        double increment = PI / 128;
        var carrot = new Carrot();
        for (double phi = 0; phi <= max; phi += increment) {
            var velocity = new Velocity(0, 0, Point1S.ZERO);
            carrotObj = new InGameObject(carrot, Vector2D.of(0, 0), Point1S.of(phi), velocity);
            frame.repaint();
            sleep(25);
        }
    }
}