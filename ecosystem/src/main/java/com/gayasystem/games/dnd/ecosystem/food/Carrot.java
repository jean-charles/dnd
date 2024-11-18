package com.gayasystem.games.dnd.ecosystem.food;

import com.gayasystem.games.dnd.common.Food;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Carrot extends Food {
    public static final double WIDTH = 0.06;
    public static final double DEPTH = 0.20;

    public Carrot() {
        super(WIDTH, DEPTH, 0.130, 4, 20);
    }
}
