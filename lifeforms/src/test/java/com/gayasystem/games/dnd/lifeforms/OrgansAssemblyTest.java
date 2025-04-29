package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.Brain;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.NeuralNetworkInputsConverter;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.VelocityFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.EngramComputing;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.Hair;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.Organ;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {OrgansAssemblyTest.class, ALifeForm.class})
public class OrgansAssemblyTest {
    @MockBean
    NeuralNetworkInputsConverter neuralNetworkInputsConverter;

    @MockBean
    BrainFactory brainFactory;

    @MockBean
    ApplicationContext applicationContext;

    @MockBean
    Brain brain;

    @MockBean
    EngramComputing engramComputing;

    @MockBean
    VelocityFactory velocityFactory;

    @MockBean
    MeasurementConvertor measurementConvertor;

    @Autowired
    ALifeForm lifeForm;

    @Test
    void test() {
        assertNotNull(lifeForm);
    }
}

@Component
class ALifeForm extends LifeForm {
    private static final Collection<Organ> theOrgans = new ArrayList<>();

    {
        theOrgans.add(new Hair());
    }

    public ALifeForm(NeuralNetworkInputsConverter neuralNetworkInputsConverter, BrainFactory brainFactory) {
        super(1, 1, 1, Gender.female, 1, 1, 1, null, 1, Emotion.neutral, null, theOrgans, neuralNetworkInputsConverter, brainFactory);
    }
}
