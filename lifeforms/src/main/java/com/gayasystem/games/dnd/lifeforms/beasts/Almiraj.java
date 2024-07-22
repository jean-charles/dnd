package com.gayasystem.games.dnd.lifeforms.beasts;

import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.characters.scores.Skills;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.gametools.dices.Dice1d6;
import com.gayasystem.games.dnd.lifeforms.Beast;

import java.util.List;

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
                60,
                0.0,
                null,
                0.0,
                List.of(Carrot.class),
                List.of(Thing.class)
        );
    }
}
