package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.drawables.Drawer;
import com.gayasystem.games.dnd.world.Object3D;
import com.gayasystem.games.dnd.world.World;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private final Drawer drawer;
    private final World world;
    private Thread worker = new Thread() {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(250);
                    world.run();
                    repaint();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public Canvas(Drawer drawer, World world) {
        super(true);
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

    private void draw(Object3D obj, Graphics g) {
        drawer.draw(getWidth(), getHeight(), obj, g);
    }
}
