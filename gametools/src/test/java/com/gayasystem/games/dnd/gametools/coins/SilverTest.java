package com.gayasystem.games.dnd.gametools.coins;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SilverTest {
    private Silver silver = Coin.silver;

    @Test
    public void toCopper() {
        assertEquals(10, silver.toCopper(1));
    }

    @Test
    public void toSilver() {
        assertEquals(1, silver.toSilver(1));
    }

    @Test
    public void toElectrum() {
        assertEquals(1, silver.toElectrum(5));
    }

    @Test
    public void toGold() {
        assertEquals(1, silver.toGold(10));
    }

    @Test
    public void toPlatinum() {
        assertEquals(1, silver.toPlatinum(100));
    }
}