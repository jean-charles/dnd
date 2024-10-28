package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.ThingA;
import com.gayasystem.games.dnd.world.WorldTestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {WorldTestConfig.class, InGameObjectsManager.class})
class InGameObjectsManagerTest {
    @Autowired
    private InGameObjectsManager manager;

    private Thing theThing;
    private Coordinate coo;
    private Orientation ori;

    @BeforeEach
    void setUp() {
        var things = manager.getAllThings();
        assertNotNull(things);
        assertEquals(0, things.size());

        theThing = new ThingA(1, 2);
        coo = new Coordinate(0, 0);
        ori = new Orientation(0);

        manager.add(theThing, coo, ori);

        assertEquals(1, manager.sizeOfInGameObjects());
        assertEquals(1, manager.sizeOfThingsByCoordinate());
        assertEquals(1, manager.sizeOfThingsLastMove());
        assertEquals(0, manager.sizeOfThingsToRemove());
    }

    @AfterEach
    void tearDown() {
        var all = manager.getAllThings();
        for (var thing : all) {
            manager.removeThing(thing);
        }
        assertEquals(all.size(), manager.sizeOfInGameObjects());
        assertEquals(all.size(), manager.sizeOfThingsByCoordinate());
        assertEquals(all.size(), manager.sizeOfThingsLastMove());
        assertEquals(all.size(), manager.sizeOfThingsToRemove());

        manager.clean();

        assertEquals(0, manager.sizeOfInGameObjects());
        assertEquals(0, manager.sizeOfThingsByCoordinate());
        assertEquals(0, manager.sizeOfThingsLastMove());
    }

    @Test
    void add() {
        var things = manager.getAllThings();
        assertNotNull(things);
        assertEquals(1, things.size());

        var t = things.stream().toArray()[0];
        assertNotNull(t);
        assertEquals(theThing, t);
    }

    @Test
    void get() {
        var igo = manager.get(theThing);
        assertNotNull(igo);
        assertEquals(theThing, igo.thing());
        assertEquals(coo, igo.coordinate());
        assertEquals(ori, igo.orientation());
    }

    @Test
    void getThing() {
        var t1 = manager.getThing(coo);
        assertNotNull(t1);
        assertEquals(theThing, t1);

        var t2 = manager.getThing(new Coordinate(1, 1));
        assertNull(t2);
    }

    @Test
    void removeThing() {
        manager.removeThing(theThing);
        var things = manager.getAllThings();
        assertNotNull(things);
        assertEquals(1, things.size());

        manager.clean();

        things = manager.getAllThings();
        assertNotNull(things);
        assertEquals(0, things.size());
    }

    @Test
    void getAll() {
        var igos = manager.getAll();
        assertNotNull(igos);
        assertEquals(1, igos.size());

        var igo = igos.stream().toList().get(0);
        assertNotNull(igo);
        assertEquals(theThing, igo.thing());
        assertEquals(coo, igo.coordinate());
        assertEquals(ori, igo.orientation());
    }
}