package com.gayasystem.games.dnd.world;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.beasts.Almiraj;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.gayasystem.games.dnd.lifeforms.Gender.female;
import static com.gayasystem.games.dnd.lifeforms.Gender.male;

@Configuration
public class BeastsConfig {
    @Bean
    public Thing almiraj1() {
        return new Almiraj(male);
    }

    @Bean
    public Thing almiraj2() {
        return new Almiraj(female);
    }
}
