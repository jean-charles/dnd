package com.gayasystem.games.dnd.drawables;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.world.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class SizeConvertor {
    @Autowired
    MeasurementConvertor convertor;

    public Point coordinate2Point(int feetWidth, int width, int height, Coordinate coordinate) {
        var pixelSizeInInches = width / convertor.feet2Inches(feetWidth);
        int x = (int) (width / 2.0 + coordinate.x().doubleValue() * pixelSizeInInches);
        int y = (int) (height / 2.0 - coordinate.y().doubleValue() * pixelSizeInInches);
        return new Point(x, y);
    }

    public int feet2Pixels(int feetWidth, int width, double feetLength) {
        var pixelSizeInInches = width / convertor.feet2Inches(feetWidth);
        var length = convertor.feet2Inches(feetLength);
        return (int) (length * pixelSizeInInches);
    }
}
