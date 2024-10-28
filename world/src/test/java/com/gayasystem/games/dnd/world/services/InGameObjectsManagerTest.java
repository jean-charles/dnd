package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.world.WorldTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {WorldTestConfig.class, InGameObjectsManager.class})
class InGameObjectsManagerTest {
    @Autowired
    private InGameObjectsManager manager;
}