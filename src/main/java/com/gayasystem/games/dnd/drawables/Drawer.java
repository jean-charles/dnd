package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
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
    private MeasurementConvertor convertor;

    @Autowired
    private SizeConvertor sizeConvertor;

    /**
     * @param feetWidth Canvas size in feet.
     * @param width     Canvas width in pixels.
     * @param height    Canvas height in pixels.
     * @param obj       {@link InGameObject} to draw in the Canvas.
     * @param g         {@link Graphics} to use to draw the {@link InGameObject}.
     */
    public void draw(int feetWidth, int width, int height, InGameObject obj, Graphics g) {
        try {
            String thingName = "drawable" + obj.thing().getClass().getSimpleName();
            Drawable drawable = (Drawable) ctx.getBean(thingName);

            int pixelsPerFoot = (int) (width / feetWidth);
            var p = sizeConvertor.coordinate2Point(pixelsPerFoot, width, height, obj.coordinate());
            drawable.draw(pixelsPerFoot, obj, p, (Graphics2D) g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
