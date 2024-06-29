package com.gayasystem.games.dnd.ecosystem.characters;

import com.gayasystem.games.dnd.characters.Character;
import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;

public class Dragonborn extends Character {
    public Dragonborn(AbilityScores abilityScores, Alignment alignment, int armorClass) {
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
                30,
                alignment,
                null,
                armorClass,
                null,
                null
        );
    }
}
