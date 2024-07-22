package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;

public class HalfElf extends Character {
    public HalfElf(AbilityScores abilityScores, Alignment alignment, int armorClass) {
        super(
                abilityScores,
                new AbilityScores(
                        null,
                        null,
                        null,
                        null,
                        null,
                        new Ability(2)
                ),
                alignment,
                null,
                armorClass,
                100, // 100 to 180 lb
                30,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
