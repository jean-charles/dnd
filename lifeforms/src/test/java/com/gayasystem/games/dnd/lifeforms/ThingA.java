package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Thing;
import org.springframework.stereotype.Component;

@Component
public class ThingA extends Thing {
    public ThingA() {
        super(0, 0, 100);
    }

    @Override
    public void run() {
    }
}
