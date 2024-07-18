package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import org.springframework.context.annotation.Bean;

public class TestConfig {
    @Bean
    public Thing aThing() {
        return new TestThing();
    }
}

class TestThing extends Thing {
    public TestThing() {
        super(100);
    }

    @Override
    public void run() {
    }
}