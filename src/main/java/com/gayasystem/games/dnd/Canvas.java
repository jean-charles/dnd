package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.drawables.Drawer;
import com.gayasystem.games.dnd.world.World;
import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Objects;

import static java.awt.Color.black;
import static java.awt.Color.white;

@Component
public class Canvas extends JPanel implements ActionListener, KeyListener {
    private static final Logger log = LoggerFactory.getLogger(Canvas.class);

    private final double metersWidth;
    // controls the delay between each tick in ms
    private final int DELAY = 250;

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

    public Canvas(double metersWidth) {
        super(true);
        setPreferredSize(new Dimension(800, 600));
        setBackground(black);
        this.metersWidth = metersWidth;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void drawBackground(Graphics2D g) {
        try {
            var img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/sand.jpeg")));
            g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), this);
        } catch (IOException e) {
        }
    }

    private void drawWorld(Graphics2D g) {
        for (var thing : world.objects()) {
            if (thing != world.player())
                drawThing(thing, g);
        }
    }

    private void drawThing(InGameObject thing, Graphics2D g) {
        var center = world.player().coordinate();
        var orientation = world.player().orientation();
        drawer.draw(metersWidth, getSize(), thing, center, orientation, g, this);
    }

    private void drawPlayer(Graphics2D g) {
        var player = world.player();
        var center = player.coordinate();
        var orientation = player.orientation();
        drawer.draw(metersWidth, getSize(), player, center, orientation, g, this);
    }

    private void drawScore(Graphics2D g) {
        String score = player.getScore();

        Font font = new Font("Serif", Font.PLAIN, 36);
        var frc = g.getFontRenderContext();
        g.setFont(font);

        int borderWidth = (int) font.getSize2D() + 5;
        g.setColor(white);
        g.fillRect(0, 0, getWidth(), borderWidth);
        g.fillRect(getWidth() - borderWidth, 0, borderWidth, getHeight());
        g.fillRect(0, getHeight() - borderWidth, getWidth(), borderWidth);
        g.fillRect(0, 0, borderWidth, getHeight());

        g.setColor(black);
        g.drawString(score, 5, borderWidth - 5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        world.run();
        player.addScore(1);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground((Graphics2D) g);
        drawWorld((Graphics2D) g);
        drawPlayer((Graphics2D) g);
        drawScore((Graphics2D) g);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
