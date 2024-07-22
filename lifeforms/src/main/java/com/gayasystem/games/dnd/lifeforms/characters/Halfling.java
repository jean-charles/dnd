package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;

public class Halfling extends Character {
    public Halfling(AbilityScores abilityScores, Alignment alignment, int armorClass) {
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
                25,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
