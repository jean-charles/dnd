package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.ecosystem.beasts.Almiraj;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.ecosystem.houses.Wall;
import com.gayasystem.games.dnd.world.World;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
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

import static java.awt.event.KeyEvent.*;

@SpringBootApplication
public class Application extends JFrame implements KeyListener {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private final ApplicationContext ctx;
    private final World world;

    private boolean isFullScreen = false;

    public Application(ApplicationContext ctx, World world) throws Exception {
        this.ctx = ctx;
        this.world = world;
        setJMenuBar(menuBar());

        gameSetUp();
        setUpUI();
        addKeyListener(this);
        setUpScreen();
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        var ctx = new SpringApplicationBuilder(Application.class)
                .headless(false).web(WebApplicationType.NONE).run(args);
        EventQueue.invokeLater(() -> {
            var ex = ctx.getBean(Application.class);
            ex.setVisible(true);
        });
    }

    private JMenuBar menuBar() {
        var menuBar = new JMenuBar();
        var menu = new JMenu();
        menu.add("Yo!");
        menuBar.add(menu);
        return null;
    }

    private void gameSetUp() {
//        world.add(newThing(Human.class), Vector2D.of(20, 20), 0);
        world.add(newThing(Almiraj.class), Vector2D.of(-15, 0), 0);
        world.add(newThing(Wall.class, 10, 1), Vector2D.of(0, 0), 0);
        world.add(newThing(Carrot.class), Vector2D.of(15, 0), 0);
    }

    private Thing newThing(Class<? extends Thing> clazz, Object... args) {
        return ctx.getBean(clazz, args);
    }

    private void setUpUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var canvas = ctx.getBean(Canvas.class, 50);
        add(canvas);
        addKeyListener(canvas);
    }

    private void fullScreen() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            isFullScreen = !isFullScreen;
            if (isFullScreen) {
//                setUndecorated(true);
                gd.setFullScreenWindow(this);
//                setVisible(true);
            } else {
//                setVisible(false);
//                setUndecorated(false);
                gd.setFullScreenWindow(null);
            }
        }
    }

    private void setUpScreen() {
        setSize(getMaximumSize());
//        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_ESCAPE, VK_SPACE:
                System.exit(0);
                break;
            case VK_F:
                fullScreen();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}