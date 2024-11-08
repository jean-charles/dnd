package com.gayasystem.games.dnd.drawables;

import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class SizeConvertor {
    /**
     * @param pixelsPerMeter pixels per meters.
     * @param width          Canvas width in pixels.
     * @param height         Canvas height in pixels.
     * @param coordinate     {@link Vector2D Coordinate} to convert.
     * @return {@link Point} in pixels.
     */
    public Point coordinate2Point(int pixelsPerMeter, int width, int height, Vector2D coordinate) {
        int x = (int) (width / 2.0 + coordinate.getX() * pixelsPerMeter);
        int y = (int) (height / 2.0 - coordinate.getY() * pixelsPerMeter);
        return new Point(x, y);
    }

    /**
     * @param metersWidth  Canvas size in meters.
     * @param width        Canvas width in pixels.
     * @param metersLength Length in meters to convert in pixels.
     * @return number of the pixels of the length.
     */
    public int meters2Pixels(int metersWidth, int width, double metersLength) {
        double pixelPerMeter = (double) width / metersWidth;
        return (int) (metersLength * pixelPerMeter);
    }
}
