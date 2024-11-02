package com.gayasystem.games.dnd.common;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Service
public class MathService {
    public static BigDecimal round(BigDecimal value) {
        return value.setScale(2, HALF_UP);
    }
    public static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        return bd.setScale(2, HALF_UP).doubleValue();
    }
}
