package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.SwingTestCase;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.drawables.SizeConvertor;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.InGameObject;
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

@SpringBootTest(classes = {DrawableCarrot.class, SizeConvertor.class, MeasurementConvertor.class})
class DrawableCarrotTest extends SwingTestCase {
    private JFrame frame;
    private JPanel panel;
    private DrawableCarrot drawableCarrot;

    private double FEET_WIDTH = 1;
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
                int pixelsPerFoot = (int) (getWidth() / FEET_WIDTH);
                if (carrotObj != null)
                    drawableCarrot.draw(pixelsPerFoot, carrotObj, p, g, null);
            }
        };
        panel.setSize(400, 400);
        panel.setBackground(black);
        panel.setDoubleBuffered(true);
        drawableCarrot = ctx.getBean(DrawableCarrot.class);
        frame = getTestFrame();
        frame.setSize(500, 500);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Test
    public void rotate() throws InterruptedException {
        double max = 2 * PI;
        double increment = PI / 128;
        var carrot = new Carrot();
        for (double phi = 0; phi <= max; phi += increment) {
            var velocity = new Velocity(0, new CircularCoordinate(0, phi));
            carrotObj = new InGameObject(carrot, new Coordinate(0, 0), velocity);
            frame.repaint();
            sleep(25);
        }
    }
}