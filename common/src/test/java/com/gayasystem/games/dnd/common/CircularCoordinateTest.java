package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.CircularCoordinate;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularCoordinateTest {
    @Test
    public void doubleInsteadOfBigDecimal() {
        var actual = new CircularCoordinate(1.2, new Orientation(BigDecimal.ONE));
        assertEquals(BigDecimal.valueOf(1.2), actual.rho());
    }
}