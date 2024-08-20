package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.ecosystem.beasts.Almiraj;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Math.PI;

@SpringBootApplication
public class Application extends JFrame implements KeyListener {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private final ApplicationContext ctx;
    private final World world;

    public static void main(String[] args) throws Exception {
        var ctx = new SpringApplicationBuilder(Application.class)
                .headless(false).web(WebApplicationType.NONE).run(args);
        EventQueue.invokeLater(() -> {
            var ex = ctx.getBean(Application.class);
            ex.setVisible(true);
        });
    }

    public Application(ApplicationContext ctx, World world) throws Exception {
        this.ctx = ctx;
        this.world = world;
        gameSetUp();
        setUpUI();
        setFullScreen();
        addKeyListener(this);
    }

    private void gameSetUp() {
//        world.add(newThing(Human.class), new Coordinate(20, 15), new Orientation(-3 * PI / 4));
        world.add(newThing(Almiraj.class), new Coordinate(-20, 0), new Orientation(0));
        world.add(newThing(Carrot.class), new Coordinate(20, 0), new Orientation(-PI / 2));
    }

    private Thing newThing(Class<? extends Thing> clazz) {
        return ctx.getBean(clazz);
    }

    private void setUpUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var canvas = ctx.getBean(Canvas.class, 60);
        add(canvas);
        addKeyListener(canvas);
    }

    private void setFullScreen() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            setUndecorated(true);
            gd.setFullScreenWindow(this);
        } else {
            log.error("Full screen not supported");
            setSize(100, 100); // just something to let you see the window
            setVisible(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}