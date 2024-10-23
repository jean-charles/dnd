package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import org.springframework.stereotype.Component;

@Component
public class ThingA extends Thing {
    public ThingA(double width, double depth) {
        super(width, depth, 100);
    }

    public ThingA() {
        this(0, 0);
    }

    @Override
    public void run() {
    }
}
