package com.gayasystem.games.dnd.neuralnetwork;

public class Layer {
    Neuron[] neurons;

    public Layer(int numNeurons, int numIputsPerNeuron) {
        neurons = new Neuron[numNeurons];
        for (int i = 0; i < numNeurons; i++)
            neurons[i] = new Neuron(numIputsPerNeuron);
    }

    public double[] feedForward(double[] inputs) {
        double[] outputs = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++)
            outputs[i] = neurons[i].activate(inputs);
        return outputs;
    }

    public void updateWeights(final double[] inputs, final double[] errors, final double learningRate) {
        for (int i = 0; i < neurons.length; i++)
            neurons[i].updateWeights(inputs, errors[i], learningRate);
    }
}
