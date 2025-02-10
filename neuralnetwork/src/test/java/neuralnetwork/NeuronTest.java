package neuralnetwork;

import com.gayasystem.games.dnd.neuralnetwork.Input;
import com.gayasystem.games.dnd.neuralnetwork.Neuron;
import com.gayasystem.games.dnd.neuralnetwork.exceptions.NeuronBuilderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NeuronTest {
    private final Input input = mock(Input.class);
    private Neuron n;

    @BeforeEach
    void setUp() throws Exception {
        n = new Neuron.Builder()
                .add(input, 1.0)
                .build();
    }

    @Test
    void missingWeight() {
        try {
            new Neuron.Builder()
                    .add(input, null)
                    .build();
            fail();
        } catch (NeuronBuilderException e) {
            assertEquals("Weight is not set!", e.getMessage());
        }
    }

    @Test
    void setValue() {
        n.setValue(input, 1);
        var r = n.result();
        assertNotNull(r);
        assertEquals(1, r);
    }

    @Test
    void setWeight() {
        n.setWeight(input, 2.0);
        n.setValue(input, 2);
        var r = n.result();
        assertNotNull(r);
        assertEquals(4, r);
    }
}