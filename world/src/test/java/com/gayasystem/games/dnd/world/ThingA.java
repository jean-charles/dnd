package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import org.springframework.stereotype.Component;

@Component
public class ThingA extends Thing {
    public ThingA() {
        super(100);
    }

    @Override
    public void run() {
    }
}
