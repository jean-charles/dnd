package com.gayasystem.games.dnd.common.weight;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Service
public class WeightConverter {
    private static final double GRAINS_IN_OUNCE = 437.5;
    private static final int OUNCES_IN_POUND = 16;
    private static final int POUNDS_IN_STONE = 14;

    @Deprecated(forRemoval = true)
    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        return bd.setScale(2, HALF_UP).doubleValue();
    }

    // Grains conversions
    public static double grainsToOunces(double grains) {
        return round(grains / GRAINS_IN_OUNCE);
    }

    public static double grainsToPounds(double grains) {
        return round(grains / (GRAINS_IN_OUNCE * OUNCES_IN_POUND));
    }

    public static double grainsToStones(double grains) {
        return round(grains / (GRAINS_IN_OUNCE * OUNCES_IN_POUND * POUNDS_IN_STONE));
    }

    // Ounces conversions
    public static double ouncesToGrains(double ounces) {
        return round(ounces * GRAINS_IN_OUNCE);
    }

    public static double ouncesToPounds(double ounces) {
        return round(ounces / OUNCES_IN_POUND);
    }

    public static double ouncesToStones(double ounces) {
        return round(ounces / (OUNCES_IN_POUND * POUNDS_IN_STONE));
    }

    // Pounds conversions
    public static double poundsToGrains(double pounds) {
        return round(pounds * OUNCES_IN_POUND * GRAINS_IN_OUNCE);
    }

    public static double poundsToOunces(double pounds) {
        return round(pounds * OUNCES_IN_POUND);
    }

    public static double poundsToStone(double pounds) {
        return round(pounds / POUNDS_IN_STONE);
    }

    // Stone conversions
    public static double stonesToGrains(double stones) {
        return round(stones * POUNDS_IN_STONE * OUNCES_IN_POUND * GRAINS_IN_OUNCE);
    }

    public static double stonesToOunces(double stones) {
        return round(stones * POUNDS_IN_STONE * OUNCES_IN_POUND);
    }

    public static double stonesToPounds(double stones) {
        return round(stones * POUNDS_IN_STONE);
    }
}
