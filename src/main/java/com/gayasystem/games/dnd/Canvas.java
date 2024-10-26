package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.drawables.Drawer;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Color.black;

@Component
public class Canvas extends JPanel implements ActionListener, KeyListener {
    private static final Logger log = LoggerFactory.getLogger(Canvas.class);

    private final int feetWidth;
    // controls the delay between each tick in ms
    private final int DELAY = 1000;

    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private World world;
    @Autowired
    private Drawer drawer;
    @Autowired
    private Player player;

    public Canvas(int feetWidth) {
        super(true);
        setPreferredSize(new Dimension(800, 600));
        setBackground(black);
        this.feetWidth = feetWidth;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void drawWorld(Graphics g) {
        for (var thing : world.objects()) {
            drawThing(thing, g);
        }
    }

    private void drawThing(InGameObject thing, Graphics g) {
        var size = getSize();
        drawer.draw(feetWidth, size, thing, g, this);
    }

    private void drawScore(Graphics g) {
    }

    private void drawBackground(Graphics g) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        world.run();

        // Prevent the player from disappearing off the board
        player.tick();

        // Give the player points for collecting coins
        // points

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWorld(g);
//        player.draw(g, this);
        drawScore(g);
        drawBackground(g);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
