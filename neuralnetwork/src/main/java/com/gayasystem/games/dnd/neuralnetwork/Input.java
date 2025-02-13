package com.gayasystem.games.dnd.neuralnetwork;

import org.springframework.lang.NonNull;

public interface Input extends Node {
    void add(@NonNull Output neuron);

    void value(@NonNull Double input);
}
