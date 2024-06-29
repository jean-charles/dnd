package com.gayasystem.games.dnd.gametools.coins;

public abstract class Coin {
    public static final double OUNCES_WEIGHT = 0.33;

    public static final Copper copper = new Copper();
    public static final Silver silver = new Silver();
    public static final Electrum electrum = new Electrum();
    public static final Gold gold = new Gold();
    public static final Platinum platinum = new Platinum();

    public abstract int toCopper(int coin);

    public abstract int toSilver(int coin);

    public abstract int toElectrum(int coin);

    public abstract int toGold(int coin);

    public abstract int toPlatinum(int coin);
}
