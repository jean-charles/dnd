package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.world.services.domains.InGameObject;
import org.apache.commons.geometry.spherical.oned.Point1S;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface Drawable {
    void draw(int pixelsPerMeter, InGameObject obj, Point point, Point1S up, Graphics g, ImageObserver observer);
}
