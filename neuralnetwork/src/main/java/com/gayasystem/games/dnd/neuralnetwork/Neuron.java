package com.gayasystem.games.dnd.neuralnetwork;

import com.gayasystem.games.dnd.neuralnetwork.exceptions.InvalidInputError;
import com.gayasystem.games.dnd.neuralnetwork.exceptions.NeuronBuilderException;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Neuron extends AbstractInputNeuron implements Output {
    private static final InputNeuron bias = new InputNeuron();
    private final Map<Input, Double> weights = new HashMap<>();
    private final Map<Input, Integer> inputs = new HashMap<>();
    private Integer result;

    private Neuron() {
    }

    @Override
    public void add(@NonNull Output neuron) {
        outputs.add(neuron);
    }

    @Override
    public void setWeight(@NonNull Input input, @NonNull Double weight) {
        this.weights.put(input, weight);
    }

    @Override
    public void setValue(@NonNull Input input, @NonNull Integer value) {
        if (!inputs.containsKey(input))
            throw new InvalidInputError(input);

        inputs.put(input, value);
        if (result != null)
            result = null;

        if (!inputs.containsValue(null))
            compute();
    }

    private void compute() {
        int computedResult = 0;

        for (var input : inputs.entrySet())
            computedResult += (int) (input.getValue() * weights.get(input.getKey()));

        result = computedResult;

        inputs.values().clear();
        if (inputs.containsKey(bias))
            bias.setValue(1);

        setValue(computedResult);
    }

    @Override
    public Integer result() {
        return result;
    }

    public static class Builder {
        private final Collection<Input> inputs = new ArrayList<>();
        private final Map<Input, Double> weights = new HashMap<>();
        private final Collection<Output> outputs = new ArrayList<>();

        public Builder add(@NonNull Input neuron, @NonNull Double weight) {
            inputs.add(neuron);
            weights.put(neuron, weight);
            return this;
        }

        public Builder biasWeight(@NonNull Double weight) {
            inputs.add(bias);
            weights.put(bias, weight);
            return this;
        }

        public Builder add(@NonNull Output neuron) {
            outputs.add(neuron);
            return this;
        }

        public Neuron build() throws NeuronBuilderException {
            final Neuron n = new Neuron();
            for (var input : weights.entrySet()) {
                if (input.getValue() == null)
                    throw new NeuronBuilderException("Weight is not set!");
                n.weights.put(input.getKey(), weights.get(input.getKey()));
            }
            for (var input : inputs) {
                n.inputs.put(input, null);
                input.add(n);
            }
            n.outputs.addAll(outputs);
            bias.setValue(1);

            return n;
        }
    }
}
