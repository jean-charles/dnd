package com.gayasystem.games.dnd.gametools.coins;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectrumTest {
    private Electrum electrum = Coin.electrum;

    @Test
    public void toCopper() {
        assertEquals(50, electrum.toCopper(1));
    }

    @Test
    public void toSilver() {
        assertEquals(5, electrum.toSilver(1));
    }

    @Test
    public void toElectrum() {
        assertEquals(1, electrum.toElectrum(1));
    }

    @Test
    public void toGold() {
        assertEquals(1, electrum.toGold(2));
    }

    @Test
    public void toPlatinum() {
        assertEquals(1, electrum.toPlatinum(20));
    }
}