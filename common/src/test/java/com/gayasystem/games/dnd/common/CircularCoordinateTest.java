package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.coordinates.PolarCoordinates;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CircularCoordinateTest {
    @Test
    public void doubleInsteadOfBigDecimal() {
        var actual = new PolarCoordinates(1.2, new Orientation(BigDecimal.ONE));
        assertEquals(BigDecimal.valueOf(1.2), actual.rho());
    }
}