package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class WorldConfig {
    @Autowired
    private Collection<? extends Thing> things;

    @Bean
    public World world() {
        return new World(things);
    }
}
