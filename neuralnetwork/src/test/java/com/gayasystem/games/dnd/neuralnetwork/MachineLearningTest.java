package com.gayasystem.games.dnd.neuralnetwork;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MachineLearningTest {
    MachineLearning ml;
    NeuralNetwork network;

    @BeforeEach
    void setUp() throws Exception {
        network = new NeuralNetwork(2, 20, 1, 0.01);
        ml = new MachineLearning(network);
    }

    @Test
    void simple() throws Exception {
        var samples = Arrays.asList(new MachineLearning.Sample(new double[]{1, 0.4}, new double[]{0.5}));
        ml.learn(samples, 10000);
        double[] outputs = network.feedForward(samples.getFirst().inputs());
        assertEquals(0.5, outputs[0], 0.000000001);

        var savedWeights = network.save();
        System.out.println("*** " + savedWeights);

        ObjectMapper om = new ObjectMapper();
        om.writeValue(new File("build/weights.json"), savedWeights);

        NeuralNetwork n = new NeuralNetwork(2, 20, 1, 0.01);
        n.load(savedWeights);
        outputs = n.feedForward(samples.getFirst().inputs());
        assertEquals(0.5, outputs[0], 0.000000001);
    }

    @Test
    void load() throws Exception {
        ObjectMapper om = new ObjectMapper();
        var savedWeights = om.readValue(new File("build/weights.json"), NeuralNetwork.Weights.class);
        System.out.println("*** " + savedWeights);

        NeuralNetwork n = new NeuralNetwork(2, 20, 1, 0.01);
        n.load(savedWeights);
        double[][] inputs = {
                {1, 0.4}
        };
        double[] outputs = outputs = n.feedForward(inputs[0]);
        assertEquals(0.5, outputs[0], 0.000000001);
    }
}