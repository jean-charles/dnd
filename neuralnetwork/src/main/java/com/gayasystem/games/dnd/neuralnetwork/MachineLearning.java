package com.gayasystem.games.dnd.neuralnetwork;

public class MachineLearning {
    private final NeuralNetwork network;

    public MachineLearning(final NeuralNetwork network) {
        this.network = network;
    }

    public void learn(final double[][] inputs, final double[][] expectedOutputs, int loop) {
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < loop; j++) {
                network.train(inputs[i], expectedOutputs[i]);
            }
        }
    }
}
