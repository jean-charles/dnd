package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.Orientation;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularCoordinateTest {
    @Test
    public void doubleInsteadOfBigDecimal() {
        var actual = PolarCoordinates.of(1.2, 0);
        assertEquals(1.2, actual.getRadius());
    }
}