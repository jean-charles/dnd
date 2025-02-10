package com.gayasystem.games.dnd.neuralnetwork;

import org.springframework.lang.NonNull;

public class InputNeuron extends AbstractInputNeuron {
    @Override
    public void add(@NonNull Output neuron) {
        outputs.add(neuron);
    }
}
