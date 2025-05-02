package com.gayasystem.games.dnd.lifeforms.body.organs.muscular;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TailTest {
    @Test
    void handleNullSignals() {
        var t = new Tail();
        try {
            t.handleSignals(null);
            fail("Expected RuntimeException");
        } catch (RuntimeException ex) {
            assertEquals("Invalid signals: null", ex.getMessage());
        }
    }

    @Test
    void handleInvalidSignals() {
        var t = new Tail();
        try {
            t.handleSignals(new double[0]);
            fail("Expected RuntimeException");
        } catch (RuntimeException ex) {
            assertEquals("Invalid signals size: 0. Expected size 1", ex.getMessage());
        }
    }
}