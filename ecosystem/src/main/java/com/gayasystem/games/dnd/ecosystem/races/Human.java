package com.gayasystem.games.dnd.ecosystem.races;

import com.gayasystem.games.dnd.ecosystem.Character;
import com.gayasystem.games.dnd.ecosystem.beasts.Almiraj;
import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.alignments.Ethical;
import com.gayasystem.games.dnd.gametools.alignments.Moral;
import com.gayasystem.games.dnd.gametools.dices.Die1D20;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Gender;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.NeuralNetworkInputsConverter;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class Human extends Character {
    public static final double WIDTH = 0.8;
    public static final double DEPTH = 0.4;

    public Human(AbilityScores abilityScores, Alignment alignment, Gender gender, NeuralNetworkInputsConverter neuralNetworkInputsConverter, BrainFactory brainFactory) {
        super(
                abilityScores,
                new AbilityScores(
                        new Ability(1),
                        new Ability(1),
                        new Ability(1),
                        new Ability(1),
                        new Ability(1),
                        new Ability(1)
                ),
                alignment,
                null,
                WIDTH,
                DEPTH,
                125, // 125 to 250 lb
                gender,
                30,
                3.0,
                0.0,
                null,
                0.0,
                Emotion.neutral,
                Map.of(
                        Almiraj.class, Emotion.hungry
                ),
                null,
                null,
                neuralNetworkInputsConverter,
                brainFactory
        );
    }

    public Human(Gender gender, NeuralNetworkInputsConverter neuralNetworkInputsConverter, BrainFactory brainFactory) {
        this(
                new AbilityScores(
                        new Ability(Die1D20.die.roll()),
                        new Ability(Die1D20.die.roll()),
                        new Ability(Die1D20.die.roll()),
                        new Ability(Die1D20.die.roll()),
                        new Ability(Die1D20.die.roll()),
                        new Ability(Die1D20.die.roll())
                ),
                new Alignment(
                        Ethical.neutral,
                        Moral.neutral
                ),
                gender,
                neuralNetworkInputsConverter,
                brainFactory
        );
    }

    public Human(NeuralNetworkInputsConverter neuralNetworkInputsConverter, BrainFactory brainFactory) {
        this(Gender.male, neuralNetworkInputsConverter, brainFactory);
    }
}
