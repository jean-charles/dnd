package com.gayasystem.games.dnd.common.wealth.coins;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldTest {
    private Gold gold = Coin.gold;

    @Test
    public void toCopper() {
        assertEquals(100, gold.toCopper(1));
    }

    @Test
    public void toSilver() {
        assertEquals(10, gold.toSilver(1));
    }

    @Test
    public void toElectrum() {
        assertEquals(2, gold.toElectrum(1));
    }

    @Test
    public void toGold() {
        assertEquals(1, gold.toGold(1));
    }

    @Test
    public void toPlatinum() {
        assertEquals(1, gold.toPlatinum(10));
    }
}