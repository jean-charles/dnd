package com.gayasystem.games.dnd.ecosystem.beasts;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.ecosystem.Beast;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.gametools.dices.Dice1d6;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.gametools.scores.Skills;
import com.gayasystem.games.dnd.lifeforms.Gender;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class Almiraj extends Beast {
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
                0,
                Dice1d6.dice,
                9,
                Gender.male,
                60,
                30.0,
                null,
                0.0,
                List.of(Carrot.class),
                List.of(Thing.class)
        );
    }
}