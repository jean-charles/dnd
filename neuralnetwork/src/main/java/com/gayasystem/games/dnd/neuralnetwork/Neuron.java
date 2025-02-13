package com.gayasystem.games.dnd.neuralnetwork;

import com.gayasystem.games.dnd.neuralnetwork.exceptions.InvalidInputError;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.random;

public class Neuron implements Input, Output {
    private static final Double LEARNING_CONSTANT = random();

    private final Collection<Input> receivedInputs = new ArrayList<>();
    private final Map<Input, Double> inputsValues = new HashMap<>();
    private final Map<Input, Double> weights = new HashMap<>();
    private final Collection<Output> outputs = new ArrayList<>();
    private final Double biasWeight = random();

    private Double value;

    public Neuron() {
    }

    @Override
    public void add(@NonNull Input neuron) {
        inputsValues.put(neuron, null);
        weights.put(neuron, random());
        neuron.add(this);
    }

    @Override
    public void add(@NonNull final Output neuron) {
        outputs.add(neuron);
    }

    @Override
    public void value(@NonNull Double input) {
    }

    @Override
    public void valueOf(@NonNull final Input input, @NonNull final Double value) {
        if (!inputsValues.containsKey(input))
            throw new InvalidInputError(input);

        inputsValues.put(input, value);
        receivedInputs.add(input);

        if (receivedInputs.size() == inputsValues.size())
            compute();
    }

    @Override
    public Double result() {
        return value;
    }

    //    @Override
    public void learn(@NonNull final Double expected) {
        inputsValues.forEach((input, theValue) -> {
            double value = theValue;
//            var error = error(expected);
//            if (error != 0) {
//                double deltaWeight = error * value * LEARNING_CONSTANT;
//                weights.compute(input, (in, weight) -> weight == null ? 0 : weight + deltaWeight);
//                if (input instanceof Output) {
//                    ((Output) input).learn(value / deltaWeight);
//                }
//                input.setValue(inputsValues.get(input));
//            }
        });
    }

    //    @Override
    public Double expected(@NonNull final Double expected) {
        return expected - value;
    }

    private void compute() {
        receivedInputs.clear();

        double computedResult = 0.0;

        for (var input : inputsValues.entrySet())
            computedResult += input.getValue() * weights.get(input.getKey());

        value = computedResult;

//        if (inputsValues.containsKey(bias))
//            bias.setValue(1.0);

//        setValue(value);
    }
}
