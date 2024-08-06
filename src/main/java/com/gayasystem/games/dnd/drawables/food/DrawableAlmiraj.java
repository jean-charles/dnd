package com.gayasystem.games.dnd.drawables.food;

import com.gayasystem.games.dnd.drawables.Drawable;
import com.gayasystem.games.dnd.world.Object3D;
import org.springframework.stereotype.Service;

import java.awt.*;

import static java.awt.Color.darkGray;
import static java.awt.Color.white;

@Service
public class DrawableAlmiraj implements Drawable {
    @Override
    public void draw(Object3D obj, Graphics g) {
        int x = obj.coordinate().x().intValue();
        int y = obj.coordinate().y().intValue();

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
