package com.gayasystem.games.dnd.ecosystem.beasts;

import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.ecosystem.Beast;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.gametools.dices.Die1D6;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.gametools.scores.Skills;
import com.gayasystem.games.dnd.lifeforms.Gender;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.neuralnetwork.NeuralNetworkConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class Almiraj extends Beast {
    private static final double WIDTH = 0.25;
    private static final double DEPTH = 0.50;

    public Almiraj() {
        super(
                new AbilityScores(
                        new Ability(2),
                        new Ability(16),
                        new Ability(10),
                        new Ability(2),
                        new Ability(14),
                        new Ability(10)
                ),
                null,
                null,
                new Skills(
                        0,
                        0,
                        0,
                        4,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        4,
                        0,
                        0,
                        0,
                        0,
                        0),
                Die1D6.die,
                WIDTH,
                DEPTH,
                MeasurementConvertor.c.poundInKg(9),
                Gender.male,
                15.24, // 50 feet/s in m/s
                3218.69, // 2 miles in m
                9.144, // 30 feet in m
                null,
                0.0,
                Emotion.neutral,
                Map.of(
                        Carrot.class, Emotion.hungry
                )
        );
    }

    @Override
    public NeuralNetworkConfig neuralNetworkConfig() {
        return new NeuralNetworkConfig(3, 20, 3, 0.01);
    }
}
