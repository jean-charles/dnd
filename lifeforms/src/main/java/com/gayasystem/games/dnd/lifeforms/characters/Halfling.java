package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;
import com.gayasystem.games.dnd.lifeforms.Gender;

public class Halfling extends Character {
    public Halfling(AbilityScores abilityScores, Alignment alignment, int armorClass, Gender gender) {
        super(
                abilityScores,
                new AbilityScores(
                        null,
                        new Ability(2),
                        null,
                        null,
                        null,
                        null
                ),
                alignment,
                null,
                armorClass,
                40, //40 to 45 lb
                gender,
                25,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
