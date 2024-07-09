package com.gayasystem.games.dnd.ecosystem.food;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTest {
    private class FoodA extends Food {
        FoodA() {
            super(125, 10, 100);
        }

        @Override
        public double nourishment() {
            return super.nourishment();
        }

        @Override
        public void run() {
            super.run();
        }
    }

    private Food food;

    @Test
    void run() {
        food = new FoodA();
        assertEquals(125, food.mass());
        assertEquals(100, food.nourishment());
        food.run();
        assertEquals(115, food.mass());
        assertEquals(90, food.nourishment());
        food.run();
        assertEquals(105, food.mass());
        assertEquals(80, food.nourishment());
        food.run();
        assertEquals(95, food.mass());
        assertEquals(70, food.nourishment());
        food.run();
        assertEquals(85, food.mass());
        assertEquals(60, food.nourishment());
        food.run();
        assertEquals(75, food.mass());
        assertEquals(50, food.nourishment());
        food.run();
        assertEquals(65, food.mass());
        assertEquals(40, food.nourishment());
        food.run();
        assertEquals(55, food.mass());
        assertEquals(30, food.nourishment());
        food.run();
        assertEquals(45, food.mass());
        assertEquals(20, food.nourishment());
        food.run();
        assertEquals(35, food.mass());
        assertEquals(10, food.nourishment());
        food.run();
        assertEquals(25, food.mass());
        assertEquals(0, food.nourishment());
        food.run();
        assertEquals(15, food.mass());
        assertEquals(0, food.nourishment());
        food.run();
        assertEquals(5, food.mass());
        assertEquals(0, food.nourishment());
        food.run();
        assertEquals(5, food.mass());
        assertEquals(0, food.nourishment());
        food.run();
        assertEquals(5, food.mass());
        assertEquals(0, food.nourishment());
    }
}
