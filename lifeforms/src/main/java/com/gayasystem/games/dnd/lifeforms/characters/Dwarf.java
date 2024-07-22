package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;

public class Dwarf extends Character {
    public Dwarf(AbilityScores abilityScores, Alignment alignment, int armorClass) {
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
                25,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
