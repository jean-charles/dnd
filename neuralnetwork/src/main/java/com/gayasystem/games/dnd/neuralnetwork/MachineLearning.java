package com.gayasystem.games.dnd.neuralnetwork;

import java.util.Collection;

public class MachineLearning {
    private final NeuralNetwork network;

    public MachineLearning(final NeuralNetwork network) {
        this.network = network;
    }

    public void learn(final Collection<Sample> samples, int loops) {
        for (var sample : samples) {
            for (int loop = 0; loop < loops; loop++) {
                network.train(sample.inputs, sample.expectedOutputs);
            }
        }
    }

    public record Sample(double[] inputs, double[] expectedOutputs) {
    }
}
