package com.gayasystem.games.dnd.ecosystem.races;

import com.gayasystem.games.dnd.ecosystem.Character;
import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Gender;

public class Dwarf extends Character {
    public Dwarf(AbilityScores abilityScores, Alignment alignment, int armorClass, Gender gender) {
        super(
                abilityScores,
                new AbilityScores(
                        null,
                        null,
                        new Ability(2),
                        null,
                        null,
                        null
                ),
                alignment,
                null,
                armorClass,
                150,
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
