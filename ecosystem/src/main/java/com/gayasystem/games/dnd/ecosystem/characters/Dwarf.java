package com.gayasystem.games.dnd.ecosystem.characters;

import com.gayasystem.games.dnd.common.characters.Character;
import com.gayasystem.games.dnd.common.characters.alignments.Alignment;
import com.gayasystem.games.dnd.common.characters.scores.Ability;
import com.gayasystem.games.dnd.common.characters.scores.AbilityScores;

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
                armorClass
        );
    }
}
