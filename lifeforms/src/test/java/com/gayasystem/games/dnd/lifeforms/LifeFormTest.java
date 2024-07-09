package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Thing;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LifeFormTest {
    private static final double MASS = 1.2;
    private static final double SPEED = 2.3;
    private static final Collection<Class<? extends Thing>> SCARED_BY = List.of();
    private static final Collection<Class<? extends Thing>> ATTRACTED_BY = List.of();

    LifeForm lifeForm = new LifeFormA(MASS, SPEED, SCARED_BY, ATTRACTED_BY);

    @Test
    void mass() {
        assertEquals(MASS, lifeForm.mass());
    }

    @Test
    void run() {
        // TODO Will be covered when LifeFord will take Brain as a Bean
    }

    @Test
    void see() {
        // TODO Will be covered when LifeFord will take Brain as a Bean
    }

    @Test
    void ear() {
        // TODO Will be covered when LifeFord will take Brain as a Bean
    }

    @Test
    void sightDistance() {
        // TODO Need setter before
    }

    private class LifeFormA extends LifeForm {
        public LifeFormA(double mass, double speed, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
            super(mass, speed, scaredBy, attractedBy);
        }
    }
}