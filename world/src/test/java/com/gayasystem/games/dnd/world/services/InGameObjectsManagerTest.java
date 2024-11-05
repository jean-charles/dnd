package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import com.gayasystem.games.dnd.world.ThingA;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {HitBoxUtils.class, HitBoxValidator.class, InGameObjectsManager.class})
class InGameObjectsManagerTest {
    @Autowired
    private InGameObjectsManager manager;

    private Thing theThing;
    private Vector2D coo;
    private double ori;

    @BeforeEach
    void setUp() {
        var things = manager.getAllThings();
        assertNotNull(things);
        assertEquals(0, things.size());

        theThing = new ThingA(1, 2);
        coo = Vector2D.of(0, 0);
        ori = 0;

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

        var t = things.toArray()[0];
        assertNotNull(t);
        assertEquals(theThing, t);
    }

    @Test
    void get() {
        var igo = manager.get(theThing);
        assertNotNull(igo);
        assertEquals(theThing, igo.thing());
        assertEquals(coo, igo.coordinate());
        assertEquals(ori, igo.velocity().destination().getAzimuth());
    }

    @Test
    void getThing() {
        var t1 = manager.getThing(coo);
        assertNotNull(t1);
        assertEquals(theThing, t1);

        var t2 = manager.getThing(Vector2D.of(1, 1));
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

        var igo = igos.stream().toList().getFirst();
        assertNotNull(igo);
        assertEquals(theThing, igo.thing());
        assertEquals(coo, igo.coordinate());
        assertEquals(ori, igo.velocity().destination().getAzimuth());
    }

    @Test
    void move() {
        PolarCoordinates ccoo = PolarCoordinates.of(120, 0);
        Velocity velocity = new Velocity(60, ccoo);

        var t0 = manager.getLastTimestamp(theThing);

        manager.move(theThing, velocity);

        var t1 = manager.getLastTimestamp(theThing);
        var igo = manager.get(theThing);
        var coo = igo.coordinate();
        var distance = coo.getX();

        double interval = (t1.getTime() - t0.getTime()) / 1000.0;
        var expectedDistance = interval * velocity.speed();
        assertEquals(expectedDistance, distance, 0.0);
        assertEquals(Vector2D.of(expectedDistance, 0), igo.coordinate());
    }

    @Test
    void moveObstructed() throws InterruptedException {
        Thing aThing = new ThingA(10, 2);
        var aCoo = Vector2D.of(10, 0);
        manager.add(aThing, aCoo, 0);

        PolarCoordinates ccoo = PolarCoordinates.of(60, 0);
        Velocity velocity = new Velocity(60, ccoo);

        var t0 = manager.getLastTimestamp(theThing);

        Thread.sleep(1000);
        manager.move(theThing, velocity);

        var t1 = manager.getLastTimestamp(theThing);
        var igo = manager.get(theThing);
        var coo = igo.coordinate();
        var distance = coo.getX();

        double interval = (t1.getTime() - t0.getTime()) / 1000.0;
        var expectedDistance = 8;
        assertEquals(expectedDistance, distance, 0.0);
        assertEquals(Vector2D.of(expectedDistance, 0), igo.coordinate());
    }
}