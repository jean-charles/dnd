package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;

public class Tiefling extends Character {
    public Tiefling(AbilityScores abilityScores, Alignment alignment, int armorClass) {
        super(
                abilityScores,
                new AbilityScores(
                        null,
                        null,
                        null,
                        new Ability(1),
                        null,
                        new Ability(2)
                ),
                alignment,
                null,
                armorClass,
                140, // 140 to 220 lb
                30,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
