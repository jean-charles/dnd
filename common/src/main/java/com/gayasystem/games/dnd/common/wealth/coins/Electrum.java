package com.gayasystem.games.dnd.common.wealth.coins;

public class Electrum extends Coin {
    Electrum() {
    }

    @Override
    public int toCopper(int coin) {
        return coin * 50;
    }

    @Override
    public int toSilver(int coin) {
        return coin * 5;
    }

    @Override
    public int toElectrum(int coin) {
        return coin;
    }

    @Override
    public int toGold(int coin) {
        return coin / 2;
    }

    @Override
    public int toPlatinum(int coin) {
        return coin / 20;
    }
}
