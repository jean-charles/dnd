package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;

public class HalfOrc extends Character {
    public HalfOrc(AbilityScores abilityScores, Alignment alignment, int armorClass) {
        super(
                abilityScores,
                new AbilityScores(
                        new Ability(2),
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                alignment,
                null,
                armorClass,
                155, // 155 to 225 lb
                30,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
