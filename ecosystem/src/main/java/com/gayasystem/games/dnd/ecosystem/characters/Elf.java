package com.gayasystem.games.dnd.ecosystem.characters;

import com.gayasystem.games.dnd.characters.Character;
import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;

public class Elf extends Character {
    public Elf(AbilityScores abilityScores, Alignment alignment, int armorClass) {
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
                100, // 100 to 145 lb
                30,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
