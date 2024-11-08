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
     * @param metersWidth Canvas width in meters.
     * @param size        Canvas {@link Dimension dimension}.
     * @param obj         {@link InGameObject} to draw in the Canvas.
     * @param g           {@link Graphics} to use to draw the {@link InGameObject}.
     */
    public void draw(int metersWidth, Dimension size, InGameObject obj, Graphics g, ImageObserver observer) {
        try {
            String thingName = "drawable" + obj.thing().getClass().getSimpleName();
            Drawable drawable = (Drawable) ctx.getBean(thingName);

            int pixelsPerMeter = size.width / metersWidth;
            var p = sizeConvertor.coordinate2Point(pixelsPerMeter, size.width, size.height, obj.coordinate());
            drawable.draw(pixelsPerMeter, obj, p, (Graphics2D) g, observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
