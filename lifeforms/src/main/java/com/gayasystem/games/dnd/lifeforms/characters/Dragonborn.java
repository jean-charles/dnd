package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;
import com.gayasystem.games.dnd.lifeforms.Gender;

public class Dragonborn extends Character {
    public Dragonborn(AbilityScores abilityScores, Alignment alignment, int armorClass, Gender gender) {
        super(
                abilityScores,
                new AbilityScores(
                        new Ability(2),
                        null,
                        null,
                        null,
                        null,
                        new Ability(1)
                ),
                alignment,
                null,
                armorClass,
                250,
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
