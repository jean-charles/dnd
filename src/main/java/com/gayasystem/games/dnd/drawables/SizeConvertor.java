package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.world.Coordinate;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class SizeConvertor {
    /**
     * @param pixelPerFoot pixels per foot.
     * @param width        Canvas width in pixels.
     * @param height       Canvas height in pixels.
     * @param coordinate   Coordinate to convert.
     * @return {@link Point} in pixels.
     */
    public Point coordinate2Point(int pixelPerFoot, int width, int height, Coordinate coordinate) {
        int x = (int) (width / 2.0 + coordinate.x().doubleValue() * pixelPerFoot);
        int y = (int) (height / 2.0 - coordinate.y().doubleValue() * pixelPerFoot);
        return new Point(x, y);
    }

    /**
     * @param feetWidth  Canvas size in feet.
     * @param width      Canvas width in pixels.
     * @param feetLength length in feet to convert in pixels.
     * @return number of the pixels of the length.
     */
    public int feet2Pixels(int feetWidth, int width, double feetLength) {
        double pixelPerFoot = (double) width / feetWidth;
        return (int) (feetLength * pixelPerFoot);
    }
}
