package com.gayasystem.games.dnd.ecosystem.characters;

import com.gayasystem.games.dnd.characters.Character;
import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;

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
                140, // 140 to 220 lb
                30,
                alignment,
                null,
                armorClass,
                null,
                null
        );
    }
}
