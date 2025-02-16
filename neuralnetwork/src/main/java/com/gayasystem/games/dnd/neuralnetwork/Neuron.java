package com.gayasystem.games.dnd.neuralnetwork;

import static java.lang.Math.exp;
import static java.lang.Math.random;

public class Neuron {
    double[] weights;
    double bias;
    private double output;

    public Neuron(final int nbInputs) {
        weights = new double[nbInputs];
        bias = random();
        initializeWeights();
    }

    public double activate(final double[] inputs) {
        double sum = bias;
        for (int i = 0; i < weights.length; i++)
            sum += inputs[i] * weights[i];
        output = sigmoid(sum);
        return output;
    }

    public double output() {
        return output;
    }

    public void updateWeights(final double[] inputs, final double error, final double learningRate) {
        for (int i = 0; i < weights.length; i++)
            weights[i] += learningRate * error * output * (1 - output * inputs[i]);
        bias += learningRate * error * output * (1 - output);
    }

    private void initializeWeights() {
        for (int i = 0; i < weights.length; i++)
            weights[i] = random();
    }

    private double sigmoid(double x) {
        return 1 / (1 + exp(-x));
    }
}
