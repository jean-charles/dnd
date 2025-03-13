package com.gayasystem.games.dnd.ecosystem.beasts;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.ecosystem.races.Human;
import com.gayasystem.games.dnd.lifeforms.LifeEnvironment;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactoryImpl;
import com.gayasystem.games.dnd.lifeforms.brain.DefaultBrain;
import com.gayasystem.games.dnd.lifeforms.brain.NeuralNetworkInputsConverter;
import com.gayasystem.games.dnd.lifeforms.brain.VelocityFactory;
import com.gayasystem.games.dnd.lifeforms.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.brain.memories.NextAction;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.neuralnetwork.MachineLearning;
import com.gayasystem.games.dnd.neuralnetwork.NeuralNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.gayasystem.games.dnd.lifeforms.brain.memories.Action.doNothing;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ApplicationContext.class, BrainFactoryImpl.class, DefaultBrain.class, Almiraj.class, NeuralNetworkInputsConverter.class, VelocityFactory.class})
class AlmirajLearning {
    static final int inputSize = 3;
    static final int outputSize = 3;
    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private NeuralNetworkInputsConverter inputsFactory;

    @Autowired
    private VelocityFactory outputsFactory;

    @MockBean
    private LifeEnvironment env;

    @MockBean
    private MeasurementConvertor mc;

    @MockBean
    private EngramComputing ec;

    @BeforeEach
    void setUp() throws Exception {
        when(ec.compute(any(Emotion.class), anyCollection(), anyCollection())).thenReturn(new NextAction(doNothing));
    }

    @Test
    void learn() throws Exception {
        var a = ctx.getBean(Almiraj.class);
        var n = new NeuralNetwork(a.neuralNetworkConfig());
        var ml = new MachineLearning(n);

        List<MachineLearning.Sample> samples = new ArrayList<>();
        samples.addAll(loop(Almiraj.class, false));
        samples.addAll(loop(Carrot.class, false));
        samples.addAll(loop(Human.class, true));

        assertEquals(561, samples.size());

        ml.learn(samples, 100000);

        for (var sample : samples) {
            var outputs = n.feedForward(sample.inputs());
            assertEquals(sample.expectedOutputs()[VelocityFactory.SPEED], outputs[VelocityFactory.SPEED], 0.01);
            assertEquals(sample.expectedOutputs()[VelocityFactory.AZIMUTH], outputs[VelocityFactory.AZIMUTH], 0.01);
        }
    }

    private List<MachineLearning.Sample> loop(final Class<? extends Thing> thing, final boolean afraid) {
        List<MachineLearning.Sample> samples = new ArrayList<>();

        for (double radius = 0.0; radius <= 10.0; radius++) {
            for (double azimuth = -PI; azimuth <= PI; azimuth += PI / 8) {
                double[] inputs = new double[inputSize];
                double[] expectedOutputs = new double[outputSize];

                inputs[NeuralNetworkInputsConverter.ENGRAM] = thing.hashCode();
                inputs[NeuralNetworkInputsConverter.RADIUS] = radius;
                inputs[NeuralNetworkInputsConverter.AZIMUTH] = azimuth;

                expectedOutputs[VelocityFactory.SPEED] = afraid ? 1 : 0.5;
                expectedOutputs[VelocityFactory.AZIMUTH] = azimuth(azimuth, afraid) / PI;

                samples.add(new MachineLearning.Sample(inputs, expectedOutputs));
            }
        }

        return samples;
    }

    private double azimuth(final double azimuth, final boolean afraid) {
        if (!afraid)
            return azimuth;
        return azimuth + (azimuth <= 0 ? PI : -PI);
    }
}
