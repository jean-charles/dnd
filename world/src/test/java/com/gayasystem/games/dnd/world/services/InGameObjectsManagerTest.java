package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.Velocity;
import com.gayasystem.games.dnd.world.ThingA;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;
import org.apache.commons.geometry.euclidean.twod.Vector2D;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {InGameObjectsManager.class, HitBoxUtils.class, PhysicalService.class})
class InGameObjectsManagerTest {
    @Autowired
    private InGameObjectsManager manager;

    private Thing theThing;
    private Vector2D coo;
    private Point1S ori;

    @BeforeEach
    void setUp() {
        var things = manager.getAllThings();
        assertNotNull(things);
        assertEquals(0, things.size());

        theThing = new ThingA(1, 2);
        coo = Vector2D.of(0, 0);
        ori = Point1S.ZERO;

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
        assertEquals(ori, igo.velocity().azimuth());
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
        assertEquals(ori, igo.velocity().azimuth());
    }

    @Test
    void move() {
        Velocity velocity = new Velocity(60, 0, Point1S.ZERO);

        var t0 = manager.getLastTimestamp(theThing);

        manager.move(theThing, Point1S.ZERO, velocity);

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
        manager.add(aThing, aCoo, Point1S.ZERO);

        Velocity velocity = new Velocity(60, 0, Point1S.ZERO);

        var t0 = manager.getLastTimestamp(theThing);

        Thread.sleep(1000);
        manager.move(theThing, Point1S.ZERO, velocity);

        var t1 = manager.getLastTimestamp(theThing);
        var igo = manager.get(theThing);
        var coo = igo.coordinate();
        var distance = coo.getX();

        double interval = (t1.getTime() - t0.getTime()) / 1000.0;
        var expectedDistance = 8;
        assertEquals(expectedDistance, distance, 0.0);
        assertEquals(Vector2D.of(expectedDistance, 0), igo.coordinate());
    }

    @Test
    void relativeCoordinates() {
        Thing from = new ThingA(1, 1);
        Thing to = new ThingA(1, 1);

        manager.add(from, Vector2D.of(1, 1), Point1S.of(PI));
        manager.add(to, Vector2D.of(2, 1 + sqrt(3)), Point1S.ZERO);

        var p = manager.relativeCoordinates(from, to);
        var expected = PolarCoordinates.of(2, 2 * PI / 3);
        assertEquals(expected.getRadius(), p.getRadius(), 0.0000000001);
        assertEquals(expected.getAzimuth(), p.getAzimuth(), 0.0000000001);
    }
}