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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class Almiraj extends Beast {
    public static final double WIDTH = MeasurementConvertor.c.feetInMeters(0.75);
    public static final double DEPTH = MeasurementConvertor.c.feetInMeters(1.6);

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
                16,
                200,
                20,
                null,
                0.0,
                Emotion.neutral,
                Map.of(
                        Carrot.class, Emotion.hungry
                )
        );
    }
}
