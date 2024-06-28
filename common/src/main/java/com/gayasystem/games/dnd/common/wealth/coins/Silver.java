package com.gayasystem.games.dnd.common.wealth.coins;

public class Silver extends Coin {
    Silver() {
    }

    @Override
    public int toCopper(int coin) {
        return coin * 10;
    }

    @Override
    public int toSilver(int coin) {
        return coin;
    }

    @Override
    public int toElectrum(int coin) {
        return coin / 5;
    }

    @Override
    public int toGold(int coin) {
        return coin / 10;
    }

    @Override
    public int toPlatinum(int coin) {
        return coin / 100;
    }
}
