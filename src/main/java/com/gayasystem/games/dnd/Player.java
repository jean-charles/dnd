package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.drawables.races.DrawableHuman;
import com.gayasystem.games.dnd.world.World;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;
import static java.lang.Math.PI;

@Component
public class Player extends DrawableHuman {
    private static final Logger log = LoggerFactory.getLogger(Player.class);
    private static final double ACCELERATION = 2.0;

    private BufferedImage image;
    private int imgHalfWidth = 0;
    private int imgHalfHeight = 0;

    private int score;

    @Autowired
    private World world;

    public Player() throws IOException {
        super(log);
        score = 0;
    }

    public void keyPressed(KeyEvent e) {
        var igo = world.player();
        var orientation = igo.orientation();
        var velocity = igo.velocity();
        var speed = velocity.speed();
        var acceleration = velocity.acceleration();

        switch (e.getKeyCode()) {
            case VK_UP, VK_W:
                velocity = new Velocity(speed, acceleration + ACCELERATION, orientation);
                break;
            case VK_DOWN, VK_S:
                velocity = new Velocity(speed, acceleration - ACCELERATION, orientation);
                break;
            case VK_RIGHT, VK_D:
                orientation = Point1S.of(orientation.getAzimuth() - PI / 8);
                velocity = new Velocity(speed, acceleration, orientation);
                break;
            case VK_LEFT, VK_A:
                orientation = Point1S.of(orientation.getAzimuth() + PI / 8);
                velocity = new Velocity(speed, acceleration, orientation);
                break;
        }
        world.movePlayer(orientation, velocity);
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }
}
