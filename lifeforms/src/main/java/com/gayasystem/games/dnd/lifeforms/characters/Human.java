package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;

public class Human extends Character {
    public Human(AbilityScores abilityScores, Alignment alignment, int armorClass) {
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
                30,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
