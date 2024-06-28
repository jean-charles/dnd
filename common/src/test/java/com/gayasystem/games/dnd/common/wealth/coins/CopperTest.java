package com.gayasystem.games.dnd.common.wealth.coins;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CopperTest {
    private Copper copper = Coin.copper;

    @Test
    public void toCopper() {
        assertEquals(1, copper.toCopper(1));
    }

    @Test
    public void toSilver() {
        assertEquals(1, copper.toSilver(10));
    }

    @Test
    public void toElectrum() {
        assertEquals(1, copper.toElectrum(50));
    }

    @Test
    public void toGold() {
        assertEquals(1, copper.toGold(100));
    }

    @Test
    public void toPlatinum() {
        assertEquals(1, copper.toPlatinum(1000));
    }
}