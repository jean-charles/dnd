package com.gayasystem.games.dnd.gametools.coins;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlatinumTest {
    private Platinum platinum = Coin.platinum;

    @Test
    public void toCopper() {
        assertEquals(1000, platinum.toCopper(1));
    }

    @Test
    public void toSilver() {
        assertEquals(100, platinum.toSilver(1));
    }

    @Test
    public void toElectrum() {
        assertEquals(20, platinum.toElectrum(1));
    }

    @Test
    public void toGold() {
        assertEquals(10, platinum.toGold(1));
    }

    @Test
    public void toPlatinum() {
        assertEquals(1, platinum.toPlatinum(1));
    }
}