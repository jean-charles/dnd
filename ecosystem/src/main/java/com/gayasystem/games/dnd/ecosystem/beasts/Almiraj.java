package com.gayasystem.games.dnd.ecosystem.beasts;

import com.gayasystem.games.dnd.characters.Beast;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.characters.scores.Skills;
import com.gayasystem.games.dnd.gametools.dices.Dice1d6;

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
                60,
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
                13,
                Dice1d6.dice
        );
    }
}
