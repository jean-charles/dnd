package com.gayasystem.games.dnd.drawables;

import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class SizeConvertor {
    /**
     * Convert in game coordinates into Canvas coordinates.
     *
     * @param pixelsPerMeter pixels per meters.
     * @param width          Canvas width in pixels.
     * @param height         Canvas height in pixels.
     * @param center         In game center coordinates to use as center of the Canvas.
     * @param coordinate     {@link Vector2D Coordinate} to convert.
     * @return {@link Point Coordinates} in pixels.
     */
    public Point coordinate2Point(int pixelsPerMeter, int width, int height, Vector2D center, Vector2D coordinate) {
        var relativeX = coordinate.getX() - center.getX();
        var relativeY = coordinate.getY() - center.getY();
        int x = (int) (width / 2.0 + relativeX * pixelsPerMeter);
        int y = (int) (height / 2.0 - relativeY * pixelsPerMeter);
        return new Point(x, y);
    }

    /**
     * @param pixelsPerMeter pixels per meters.
     * @param metersLength   Length in meters to convert in pixels.
     * @return number of the pixels of the length.
     */
    public int meters2Pixels(int pixelsPerMeter, double metersLength) {
        return (int) (metersLength * pixelsPerMeter);
    }
}
