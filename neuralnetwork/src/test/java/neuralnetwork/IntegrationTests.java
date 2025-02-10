package neuralnetwork;

import com.gayasystem.games.dnd.neuralnetwork.InputNeuron;
import com.gayasystem.games.dnd.neuralnetwork.Neuron;
import com.gayasystem.games.dnd.neuralnetwork.Output;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTests {
    private InputNeuron input1;
    private InputNeuron input2;

    private Output output;

    @BeforeEach
    void setUp() throws Exception {
        input1 = new InputNeuron();
        input2 = new InputNeuron();
        output = new Neuron.Builder()
                .biasWeight(-0.5)
                .add(input1, 0.5)
                .add(input2, -1.0)
                .build();
    }

    @Test
    void simple() {
        input1.setValue(12);
        input2.setValue(4);
        var r = output.result();
        assertEquals(2, r);
    }
}
