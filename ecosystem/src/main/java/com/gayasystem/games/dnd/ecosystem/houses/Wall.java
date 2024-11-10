package com.gayasystem.games.dnd.ecosystem.houses;

import com.gayasystem.games.dnd.common.Thing;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Wall extends Thing {
    public Wall(double width, double depth) {
        super(width, depth, Integer.MAX_VALUE);
    }

    @Override
    public void run() {
    }
}
