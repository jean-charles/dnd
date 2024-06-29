package com.gayasystem.games.dnd.common.weight;

import com.gayasystem.games.dnd.common.wealth.coins.Coin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightConverterTest {
    @Test
    public void grainsToOunces() {
        assertEquals(1, WeightConverter.grainsToOunces(437.5), 0.0);
    }

    @Test
    public void grainsToPounds() {
        assertEquals(1, WeightConverter.grainsToPounds(7000), 0.0);
    }

    @Test
    public void grainsToStones() {
        assertEquals(1, WeightConverter.grainsToStones(98000), 0.0);
    }

    @Test
    public void ouncesToGrains() {
        assertEquals(437.5, WeightConverter.ouncesToGrains(1), 0.0);
    }

    @Test
    public void ouncesToPounds() {
        assertEquals(1, WeightConverter.ouncesToPounds(16), 0.0);
    }

    @Test
    public void ouncesToStones() {
        assertEquals(1, WeightConverter.ouncesToStones(224), 0.0);
    }

    @Test
    public void poundsToGrains() {
        assertEquals(7000, WeightConverter.poundsToGrains(1), 0.0);
    }

    @Test
    public void poundsToOunces() {
        assertEquals(16, WeightConverter.poundsToOunces(1), 0.0);
    }

    @Test
    public void poundsToStone() {
        assertEquals(1, WeightConverter.poundsToStone(14), 0.0);
    }

    @Test
    public void stonesToGrains() {
        assertEquals(98000, WeightConverter.stonesToGrains(1), 0.0);
    }

    @Test
    public void stonesToOunces() {
        assertEquals(224, WeightConverter.stonesToOunces(1), 0.0);
    }

    @Test
    public void stonesToPounds() {
        assertEquals(14, WeightConverter.stonesToPounds(1), 0.0);
    }

    @Test
    public void coinsToPounds() {
        assertEquals(1, WeightConverter.ouncesToPounds(50 * Coin.OUNCES_WEIGHT), 0.04);
    }
}