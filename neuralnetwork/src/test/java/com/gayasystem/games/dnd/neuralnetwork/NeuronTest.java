package com.gayasystem.games.dnd.neuralnetwork;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NeuronTest {
    private final NeuralNetwork network = new NeuralNetwork(2, 0, 1, 0);

    @Test
    void setValue() {
        double[] inputs = {12, 4};
        double[] outputs = network.feedForward(inputs);
        assertNotNull(outputs);
        assertEquals(1, outputs.length);
        assertNotNull(outputs[0]);
    }
}