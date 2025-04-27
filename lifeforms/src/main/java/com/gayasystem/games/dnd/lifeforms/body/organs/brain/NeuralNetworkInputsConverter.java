package com.gayasystem.games.dnd.lifeforms.body.organs.brain;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.Engram;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.Organ;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class NeuralNetworkInputsConverter {
    public int inputSize(Collection<Organ> organs) {
        int nbSignals = 0;
        for (var organ : organs)
            nbSignals += organ.nbSignals();
        return nbSignals;
    }

    public double[] create(Collection<Engram> engrams, int nbSignals) {
        double[] inputs = new double[nbSignals];
        int offset = 0;
        for (var engram : engrams) {
            var values = engram.values();
            System.arraycopy(values, 0, inputs, offset, values.length);
            offset += values.length;
        }
        return inputs;
    }
}
