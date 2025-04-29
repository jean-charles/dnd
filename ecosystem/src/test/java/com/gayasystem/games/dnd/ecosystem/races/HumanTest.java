package com.gayasystem.games.dnd.ecosystem.races;

import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.alignments.Ethical;
import com.gayasystem.games.dnd.gametools.alignments.Moral;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Gender;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.NeuralNetworkInputsConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HumanTest {
    @MockBean
    NeuralNetworkInputsConverter neuralNetworkInputsConverter;

    @MockBean
    BrainFactory brainFactory;

    @Test
    public void characterCreation() {
        AbilityScores abilityScores = new AbilityScores();
        Alignment alignment = new Alignment(Ethical.neutral, Moral.neutral);
        Gender gender = Gender.male;
        var h = new Human(abilityScores, alignment, gender, neuralNetworkInputsConverter, brainFactory);
        assertNotNull(h);
    }
}