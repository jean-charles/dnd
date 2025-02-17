package com.gayasystem.games.dnd.neuralnetwork;

import java.util.Arrays;

public class NeuralNetwork {
    private final Layer hiddenLayer;
    private final Layer outputLayer;
    private double learningRate;

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize, double learningRate) {
        this.learningRate = learningRate;
        hiddenLayer = new Layer(hiddenSize, inputSize);
        outputLayer = new Layer(outputSize, hiddenSize);
    }

    public NeuralNetwork(NeuralNetworkConfig config) {
        this(config.inputSize(), config.hiddenSize(), config.outputSize(), config.learningRate());
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

    public Weights save() {
        double[] hiddenBiasWeights = new double[hiddenLayer.neurons.length];
        for (int i = 0; i < hiddenBiasWeights.length; i++)
            hiddenBiasWeights[i] = hiddenLayer.neurons[i].bias;

        double[][] hiddenWeights = new double[hiddenLayer.neurons.length][hiddenLayer.neurons[0].weights.length];
        for (int i = 0; i < hiddenWeights.length; i++)
            hiddenWeights[i] = hiddenLayer.neurons[i].weights;

        double[] outputsBiasWeights = new double[outputLayer.neurons.length];
        for (int i = 0; i < outputsBiasWeights.length; i++)
            outputsBiasWeights[i] = outputLayer.neurons[i].bias;

        double[][] outputsWeights = new double[outputLayer.neurons.length][outputLayer.neurons[0].weights.length];
        for (int i = 0; i < outputsWeights.length; i++)
            outputsWeights[i] = outputLayer.neurons[i].weights;

        return new Weights(hiddenWeights, hiddenBiasWeights, outputsWeights, outputsBiasWeights);
    }

    public void load(Weights weights) {
        load(weights.hiddenWeights, weights.hiddenBiasWeights, weights.outputsWeights, weights.outputsBiasWeights);
    }

    public void load(double[][] hiddenWeights, double[] hiddenBiasWeights, double[][] outputsWeights, double[] outputsBiasWeights) {
        for (int i = 0; i < hiddenBiasWeights.length; i++)
            hiddenLayer.neurons[i].bias = hiddenBiasWeights[i];
        for (int i = 0; i < hiddenWeights.length; i++)
            hiddenLayer.neurons[i].weights = hiddenWeights[i];
        for (int i = 0; i < outputsBiasWeights.length; i++)
            outputLayer.neurons[i].bias = outputsBiasWeights[i];
        for (int i = 0; i < outputsWeights.length; i++)
            outputLayer.neurons[i].weights = outputsWeights[i];
    }

    public record Weights(double[][] hiddenWeights, double[] hiddenBiasWeights, double[][] outputsWeights,
                          double[] outputsBiasWeights) {

        @Override
        public String toString() {
            return "Weights{" +
                    "hiddenBiasWeights=" + Arrays.toString(hiddenBiasWeights) +
                    ", hiddenWeights=" + Arrays.toString(hiddenWeights) +
                    ", outputsWeights=" + Arrays.toString(outputsWeights) +
                    ", outputsBiasWeights=" + Arrays.toString(outputsBiasWeights) +
                    '}';
        }
    }
}
