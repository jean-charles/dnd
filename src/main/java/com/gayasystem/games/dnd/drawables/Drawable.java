package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.world.Object3D;

import java.awt.*;

public interface Drawable {
    void draw(int width, int height, Object3D obj, Point point, Graphics g);
}
