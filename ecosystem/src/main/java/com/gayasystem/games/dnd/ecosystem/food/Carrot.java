package com.gayasystem.games.dnd.ecosystem.food;

import com.gayasystem.games.dnd.common.Food;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Carrot extends Food {
    public Carrot() {
        super(0.13, 4, 20);
    }
}
