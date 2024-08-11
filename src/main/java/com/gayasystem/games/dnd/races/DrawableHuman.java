package com.gayasystem.games.dnd.races;

import com.gayasystem.games.dnd.drawables.Drawable;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.stereotype.Service;

import java.awt.*;

import static java.awt.Color.darkGray;
import static java.awt.Color.white;

@Service
public class DrawableHuman implements Drawable {
    @Override
    public void draw(int width, int height, InGameObject obj, Point point, Graphics2D g) {
        int x = point.x;
        int y = point.y;

        g.setColor(darkGray);
        Polygon p = new Polygon(new int[]{x - 5, x, x + 5}, new int[]{y - 25, y - 60, y - 25}, 3);
        g.drawPolygon(p);
        g.fillPolygon(p);

        g.setColor(white);
        g.fillOval(x - 20, y - 25, 40, 50);
        g.fillOval(x - 20, y - 50, 15, 50);
        g.fillOval(x + 5, y - 50, 15, 50);
    }
}
