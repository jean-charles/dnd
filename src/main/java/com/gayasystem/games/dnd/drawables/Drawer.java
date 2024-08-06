package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.world.Object3D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class Drawer {
    @Autowired
    private ApplicationContext ctx;

    public void draw(Object3D obj, Graphics g) {
        try {
            String thingName = "drawable" + obj.thing().getClass().getSimpleName();
            Drawable drawable = (Drawable) ctx.getBean(thingName);
            drawable.draw(obj, g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
