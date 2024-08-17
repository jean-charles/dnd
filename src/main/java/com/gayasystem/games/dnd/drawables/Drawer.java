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
    private SizeConvertor convertor;

    public void draw(int feetWidth, int width, int height, InGameObject obj, Graphics g) {
        try {
            String thingName = "drawable" + obj.thing().getClass().getSimpleName();
            Drawable drawable = (Drawable) ctx.getBean(thingName);

            var p = convertor.coordinate2Point(feetWidth, width, height, obj.coordinate());
            drawable.draw(feetWidth, width, height, obj, p, (Graphics2D) g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
