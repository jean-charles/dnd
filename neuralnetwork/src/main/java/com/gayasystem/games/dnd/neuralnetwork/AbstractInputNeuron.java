package com.gayasystem.games.dnd.neuralnetwork;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractInputNeuron implements Input {
    protected final Collection<Output> outputs = new ArrayList<>();

    @Override
    public void setValue(@NonNull Integer input) {
        for (var output : outputs)
            output.setValue(this, input);
    }
}
