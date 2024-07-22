package com.gayasystem.games.dnd.lifeforms.characters;

import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Character;

public class Gnome extends Character {
    public Gnome(AbilityScores abilityScores, Alignment alignment, int armorClass) {
        super(
                abilityScores,
                new AbilityScores(
                        null,
                        null,
                        null,
                        new Ability(2),
                        null,
                        null
                ),
                alignment,
                null,
                armorClass,
                40, // 40 to 45 lb
                25,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
