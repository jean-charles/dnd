package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.ecosystem.beasts.Almiraj;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeastsConfig {
    @Bean
    public Thing almiraj() {
        return new Almiraj();
    }
}
