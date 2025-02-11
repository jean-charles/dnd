package com.gayasystem.games.dnd.neuralnetwork;

import org.junit.jupiter.api.Test;

class MachineLearningTest {
    @Test
    void simple() throws Exception {
        MachineLearning ml = new MachineLearning.Builder()
                .addInput("x0")
                .addInput("x1")
                .addOutput("output")
                .build();
        ml.set("x0", 12);
        ml.set("x1", 4);

        ml.expected("output", 2);
    }
}