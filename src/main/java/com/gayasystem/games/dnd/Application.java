package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.world.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private Collection<Thing> things;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var world = new World(things);
        while (true) {
            world.run();
        }
    }
}