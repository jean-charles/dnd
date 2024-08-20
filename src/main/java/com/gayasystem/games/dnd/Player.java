package com.gayasystem.games.dnd;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Objects;

@Component
public class Player {
    // image that represents the player's position on the board
    private BufferedImage image;
    // current position of the player on the board grid
    private Point position;
    // keep track of the player's score
    private int score;

    public Player() {
        // load the assets
        loadImage();

        // initialize the state
        position = new Point(0, 0);
        score = 0;
    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            image = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/races/HumanFemale.png")));
        } catch (IOException e) {
            System.out.println("Error opening image file: " + e.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                position.x,
                position.y,
                observer
        );
    }

    public void keyPressed(KeyEvent e) {
        // every keyboard get has a certain code. get the value of that code from the
        // keyboard event so that we can compare it to KeyEvent constants
        int key = e.getKeyCode();

        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input
        if (key == KeyEvent.VK_UP) {
            position.translate(0, -1);
        }
        if (key == KeyEvent.VK_RIGHT) {
            position.translate(1, 0);
        }
        if (key == KeyEvent.VK_DOWN) {
            position.translate(0, 1);
        }
        if (key == KeyEvent.VK_LEFT) {
            position.translate(-1, 0);
        }
    }

    public void tick() {
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.

        // prevent the player from moving off the edge of the board sideways
        if (position.x < 0) {
            position.x = 0;
//        } else if (pos.x >= Board.COLUMNS) {
//            pos.x = Board.COLUMNS - 1;
        }
        // prevent the player from moving off the edge of the board vertically
        if (position.y < 0) {
            position.y = 0;
//        } else if (pos.y >= Board.ROWS) {
//            pos.y = Board.ROWS - 1;
        }
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Point getPosition() {
        return position;
    }
}
