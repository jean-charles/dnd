package com.gayasystem.games.dnd.neuralnetwork;

import org.springframework.lang.NonNull;

public interface Output extends Node {
    void add(@NonNull Input neuron);

    void valueOf(@NonNull Input input, @NonNull Double value);

    Double result();
//
//    void learn(Double expected);
//
//    Double error(Double expected);
}
