package com.gayasystem.games.dnd.neuralnetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class NeuronTest {
    private final Input input = mock(Input.class);
    private Neuron n;

    @BeforeEach
    void setUp() throws Exception {
        n = new Neuron();
        n.add(input);
    }

    @Test
    void setValue() {
        n.valueOf(input, 1.0);
        Double r = n.result();
        assertNotNull(r);
    }
}