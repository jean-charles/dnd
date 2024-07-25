package com.gayasystem.games.dnd.common;

import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.common.coordinates.SphericalCoordinate;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphericalCoordinateTest {
    @Test
    public void doubleInsteadOfBigDecimal() {
        var actual = new SphericalCoordinate(1.2, new Orientation(BigDecimal.ONE, BigDecimal.TWO));
        assertEquals(BigDecimal.valueOf(1.2), actual.rho());
    }
}