package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.*;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.body.organs.muscular.Tail;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.Hair;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.Pressure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {
        ApplicationContext.class,
        NeuralNetworkInputsConverter.class,
        Brain.class,
        DefaultBrain.class,
        BrainFactory.class,
        BrainFactoryImpl.class,
        EngramComputing.class,
        VelocityFactory.class,
        MeasurementConvertor.class,
        ALifeForm.class
})
public class OrgansAssemblyTest {
    @Autowired
    ALifeForm lifeForm;

    @Test
    void test() {
        assertNotNull(lifeForm);
        var hair = (Hair) lifeForm.sensitiveOrgans.iterator().next();
        assertNotNull(hair);
        hair.stimulate(new Pressure(0.1));
        lifeForm.run();
        var organs = lifeForm.muscularOrgans;
        var tail = (Tail) organs.iterator().next();
        assertEquals(1.0, tail.speed());
    }
}

@Component
class ALifeForm extends LifeForm {
    public ALifeForm(NeuralNetworkInputsConverter neuralNetworkInputsConverter, BrainFactory brainFactory) {
        super(1, 1, 1, Gender.female, 1, 1, 1, null, 1, Emotion.neutral, null, Arrays.asList(new Hair()), Arrays.asList(new Tail()), neuralNetworkInputsConverter, brainFactory);
    }
}
