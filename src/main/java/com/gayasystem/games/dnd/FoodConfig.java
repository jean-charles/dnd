package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FoodConfig {
    @Bean
    public Thing carrot() {
        return new Carrot();
    }
}
