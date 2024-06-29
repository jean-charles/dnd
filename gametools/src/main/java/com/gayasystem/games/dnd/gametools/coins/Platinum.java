package com.gayasystem.games.dnd.gametools.coins;

public class Platinum extends Coin {
    Platinum() {
    }

    @Override
    public int toCopper(int coin) {
        return coin * 1000;
    }

    @Override
    public int toSilver(int coin) {
        return coin * 100;
    }

    @Override
    public int toElectrum(int coin) {
        return coin * 20;
    }

    @Override
    public int toGold(int coin) {
        return coin * 10;
    }

    @Override
    public int toPlatinum(int coin) {
        return coin;
    }
}
