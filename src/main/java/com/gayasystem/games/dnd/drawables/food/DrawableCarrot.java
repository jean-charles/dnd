package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.drawables.Drawable;
import com.gayasystem.games.dnd.world.Object3D;
import org.springframework.stereotype.Service;

import java.awt.*;

import static java.awt.Color.green;
import static java.awt.Color.orange;

@Service
public class DrawableCarrot implements Drawable {
    @Override
    public void draw(int width, int height, Object3D obj, Point point, Graphics g) {
        int x = point.x;
        int y = point.y;

        g.setColor(orange);
        var xs = new int[]{x - 10, x, x + 10};
        var ys = new int[]{y - 10, y + 20, y - 10};
        Polygon p = new Polygon(xs, ys, xs.length);
        g.drawPolygon(p);
        g.fillPolygon(p);

        g.setColor(green);
        g.drawLine(x, y - 11, x - 10, y - 27);
        g.drawLine(x, y - 11, x, y - 27);
        g.drawLine(x, y - 11, x + 10, y - 27);
    }
}
