package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;

public class ThingB extends Thing {
    protected ThingB() {
        super(0, 0, 1);
    }

    @Override
    public double mass() {
        return 0;
    }

    @Override
    public void run() {
    }
}
