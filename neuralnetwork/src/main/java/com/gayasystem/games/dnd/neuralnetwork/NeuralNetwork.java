package com.gayasystem.games.dnd.neuralnetwork;

public class NeuralNetwork {
    Layer hiddenLayer;
    Layer outputLayer;
    private double learningRate;

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize, double learningRate) {
        this.learningRate = learningRate;
        hiddenLayer = new Layer(hiddenSize, inputSize);
        outputLayer = new Layer(outputSize, hiddenSize);
    }

    public double[] feedForward(double[] inputs) {
        double[] hiddenOutputs = hiddenLayer.feedForward(inputs);
        return outputLayer.feedForward(hiddenOutputs);
    }

    public void train(double[] inputs, double[] expectedOutputs) {
        double[] hiddenOutputs = hiddenLayer.feedForward(inputs);
        double[] outputs = outputLayer.feedForward(hiddenOutputs);

        double[] outputErrors = new double[outputs.length];
        for (int i = 0; i < outputErrors.length; i++)
            outputErrors[i] = expectedOutputs[i] - outputs[i];

        outputLayer.updateWeights(hiddenOutputs, outputErrors, learningRate);

        double[] hiddenErrors = new double[hiddenOutputs.length];
        for (int i = 0; i < hiddenErrors.length; i++) {
            hiddenErrors[i] = 0;
            for (int j = 0; j < outputErrors.length; j++) {
                hiddenErrors[i] += outputErrors[j] * outputLayer.neurons[j].weights[i];
            }
        }

        hiddenLayer.updateWeights(inputs, hiddenErrors, learningRate);
    }
}
