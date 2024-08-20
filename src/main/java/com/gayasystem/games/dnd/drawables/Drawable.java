package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.world.InGameObject;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface Drawable {
    void draw(int pixelsPerFoot, InGameObject obj, Point point, Graphics g, ImageObserver observer);
}
