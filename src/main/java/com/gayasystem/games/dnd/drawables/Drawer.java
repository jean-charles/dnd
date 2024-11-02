package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.ImageObserver;

@Service
public class Drawer {
    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private MeasurementConvertor convertor;

    @Autowired
    private SizeConvertor sizeConvertor;

    /**
     * @param feetWidth Canvas width in feet.
     * @param size      Canvas {@link Dimension dimension}.
     * @param obj       {@link InGameObject} to draw in the Canvas.
     * @param g         {@link Graphics} to use to draw the {@link InGameObject}.
     */
    public void draw(int feetWidth, Dimension size, InGameObject obj, Graphics g, ImageObserver observer) {
        try {
            String thingName = "drawable" + obj.thing().getClass().getSimpleName();
            Drawable drawable = (Drawable) ctx.getBean(thingName);

            int pixelsPerFoot = size.width / feetWidth;
            var p = sizeConvertor.coordinate2Point(pixelsPerFoot, size.width, size.height, obj.coordinate());
            drawable.draw(pixelsPerFoot, obj, p, (Graphics2D) g, observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
