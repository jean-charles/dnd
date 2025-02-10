package com.gayasystem.games.dnd.neuralnetwork.exceptions;

import com.gayasystem.games.dnd.neuralnetwork.Input;

public class InvalidInputError extends Error {

    public InvalidInputError(Input input) {
        super("Input not found in configured input: %s".formatted(input.toString()));
    }
}
