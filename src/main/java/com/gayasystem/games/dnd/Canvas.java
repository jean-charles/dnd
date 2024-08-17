package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.drawables.Drawer;
import com.gayasystem.games.dnd.world.InGameObject;
import com.gayasystem.games.dnd.world.World;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private final int feetWidth;
    private final Drawer drawer;
    private final World world;
    private Thread worker = new Thread() {
        @Override
        public void run() {
            try {
                while (true) {
                    world.run();
                    repaint();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    };

    public Canvas(int feetWidth, Drawer drawer, World world) {
        super(true);
        this.feetWidth = feetWidth;
        this.drawer = drawer;
        this.world = world;
        worker.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameUpdate(g);
    }

    private void gameUpdate(Graphics g) {
        var objs = world.objects();
        for (var obj : objs) {
            draw(obj, g);
        }
    }

    private void draw(InGameObject obj, Graphics g) {
        drawer.draw(feetWidth, getWidth(), getHeight(), obj, g);
    }
}
