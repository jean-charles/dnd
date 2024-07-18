package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.lifeforms.brain.BrainFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Autowired
    private BrainFactory brainFactory;

//    private World world;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            while (true) {
//                world.run();
//            }
//        };
//    }
}