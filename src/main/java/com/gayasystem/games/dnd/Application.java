package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.ecosystem.Character;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.ecosystem.races.Human;
import com.gayasystem.games.dnd.lifeforms.Gender;
import com.gayasystem.games.dnd.world.World;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;
import static java.lang.Math.PI;

@SpringBootApplication
public class Application extends JFrame implements KeyListener {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private final ApplicationContext ctx;
    private final World world;

    @Autowired
    private Player player;

    private boolean isFullScreen = false;

    public Application(ApplicationContext ctx, World world) throws Exception {
        this.ctx = ctx;
        this.world = world;
        setJMenuBar(menuBar());

        gameSetUp();
        setUpUI(4.0);
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
        world.setPlayer(newPlayer(Human.class, Gender.female), Vector2D.of(0, 0), Point1S.ZERO);
//        world.add(newThing(Almiraj.class), Vector2D.of(-1.2, 0), 0);
//        world.add(newThing(Wall.class, 1, 0.05), Vector2D.of(0, 0), 0);
        world.add(newThing(Carrot.class), Vector2D.of(0, 0.9), Point1S.of(PI / 2));
    }

    private Character newPlayer(Class<? extends Character> clazz, Object... args) {
        return ctx.getBean(clazz, args);
    }

    private Thing newThing(Class<? extends Thing> clazz, Object... args) {
        return ctx.getBean(clazz, args);
    }

    private void setUpUI(double width) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var canvas = ctx.getBean(Canvas.class, width);
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
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}