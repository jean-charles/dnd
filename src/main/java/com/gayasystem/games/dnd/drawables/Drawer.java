package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class Drawer {
    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private CoordinateConvertor convertor;

    public void draw(int feet, int width, int height, InGameObject obj, Graphics g) {
        try {
            String thingName = "drawable" + obj.thing().getClass().getSimpleName();
            Drawable drawable = (Drawable) ctx.getBean(thingName);

            var p = convertor.coordinate2Point(feet, width, height, obj.coordinate());
            drawable.draw(width, height, obj, p, (Graphics2D) g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
