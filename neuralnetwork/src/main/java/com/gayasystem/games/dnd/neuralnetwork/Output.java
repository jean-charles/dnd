package com.gayasystem.games.dnd.neuralnetwork;

import org.springframework.lang.NonNull;

public interface Output extends Node {
    void setWeight(@NonNull Input input, @NonNull Double weight);

    void setValue(@NonNull Input input, @NonNull Integer value);

    Integer result();

    void learn(Integer expected);

    Integer error(Integer expected);
}
