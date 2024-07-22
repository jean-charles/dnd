package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;
import com.gayasystem.games.dnd.lifeforms.Gender;

public class Human extends Character {
    public Human(AbilityScores abilityScores, Alignment alignment, int armorClass, Gender gender) {
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
                armorClass,
                125, // 125 to 250 lb
                gender,
                30,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
