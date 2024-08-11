package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.world.InGameObject;

import java.awt.*;

public interface Drawable {
    void draw(int width, int height, InGameObject obj, Point point, Graphics2D g);
}
