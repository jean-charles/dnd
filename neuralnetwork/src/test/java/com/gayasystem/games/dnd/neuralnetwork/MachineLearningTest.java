package com.gayasystem.games.dnd.neuralnetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        double[][] inputs = {
                {1, 0.4}
        };
        double[][] expected = {
                {0.5}
        };
        ml.learn(inputs, expected, 10000);
        double[] outputs = network.feedForward(inputs[0]);
        assertEquals(0.5, outputs[0], 0.000000001);


        double[] hiddenBiasWeights = new double[network.hiddenLayer.neurons.length];
        for (int i = 0; i < hiddenBiasWeights.length; i++)
            hiddenBiasWeights[i] = network.hiddenLayer.neurons[i].bias;

        double[][] hiddenWeights = new double[network.hiddenLayer.neurons.length][network.hiddenLayer.neurons[0].weights.length];
        for (int i = 0; i < hiddenWeights.length; i++)
            hiddenWeights[i] = network.hiddenLayer.neurons[i].weights;

        double[] outputsBiasWeights = new double[network.outputLayer.neurons.length];
        for (int i = 0; i < outputsBiasWeights.length; i++)
            outputsBiasWeights[i] = network.outputLayer.neurons[i].bias;

        double[][] outputsWeights = new double[network.outputLayer.neurons.length][network.outputLayer.neurons[0].weights.length];
        for (int i = 0; i < outputsWeights.length; i++)
            outputsWeights[i] = network.outputLayer.neurons[i].weights;

        NeuralNetwork n = new NeuralNetwork(2, 20, 1, 0.01);
        for (int i = 0; i < hiddenBiasWeights.length; i++)
            n.hiddenLayer.neurons[i].bias = hiddenBiasWeights[i];
        for (int i = 0; i < hiddenWeights.length; i++)
            n.hiddenLayer.neurons[i].weights = hiddenWeights[i];
        for (int i = 0; i < outputsBiasWeights.length; i++)
            n.outputLayer.neurons[i].bias = outputsBiasWeights[i];
        for (int i = 0; i < outputsWeights.length; i++)
            n.outputLayer.neurons[i].weights = outputsWeights[i];
        outputs = n.feedForward(inputs[0]);
        assertEquals(0.5, outputs[0], 0.000000001);
    }
}