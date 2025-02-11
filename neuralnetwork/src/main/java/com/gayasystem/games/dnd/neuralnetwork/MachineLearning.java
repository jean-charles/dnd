package com.gayasystem.games.dnd.neuralnetwork;

import com.gayasystem.games.dnd.neuralnetwork.exceptions.NeuronBuilderException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MachineLearning {
    private final Map<String, Input> inputs = new HashMap<>();
    private final Map<String, Output> outputs = new HashMap<>();

    private MachineLearning() {
    }

    public void set(String name, Integer value) {
        var input = inputs.get(name);
        input.setValue(value);
    }

    public void expected(String name, Integer expected) {
    }

    public static class Builder {
        private final Collection<String> inputs = new ArrayList<>();
        private final Collection<String> outputs = new ArrayList<>();
        private final Collection<Integer> nbHiddenNeural = new ArrayList<>();

        public Builder addInput(String name) {
            inputs.add(name);
            return this;
        }

        public Builder addOutput(String name) {
            outputs.add(name);
            return this;
        }

        public Builder setNbHidden(Integer nb) {
            nbHiddenNeural.add(nb);
            return this;
        }

        public MachineLearning build() throws NeuronBuilderException {
            var ml = new MachineLearning();
            for (var name : inputs)
                ml.inputs.put(name, new InputNeuron());

            Collection<Input> latestInputs = ml.inputs.values();
            for (var nbHidden : nbHiddenNeural) {
                Collection<Input> hiddenNeurons = new ArrayList<>(nbHidden);
                for (int i = 0; i < nbHidden; i++)
                    hiddenNeurons.add(newOutput(latestInputs));
                latestInputs = hiddenNeurons;
            }

            for (var name : outputs)
                ml.outputs.put(name, newOutput(latestInputs));

            return ml;
        }

        private Neuron newOutput(Collection<Input> inputs) throws NeuronBuilderException {
            Neuron.Builder nb = new Neuron.Builder();
            for (var input : inputs)
                nb.add(input, 0.0);
            return nb.build();
        }
    }
}
