package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.beasts.Almiraj;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.gayasystem.games.dnd.lifeforms.Gender.male;

@TestConfiguration
public class WorldTestConfig {
    @Bean
    public Thing almiraj() {
        return new Almiraj(male);
    }
}
