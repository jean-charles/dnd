package com.gayasystem.games.dnd.ecosystem.characters;

import com.gayasystem.games.dnd.common.characters.Character;
import com.gayasystem.games.dnd.common.characters.alignments.Alignment;
import com.gayasystem.games.dnd.common.characters.scores.Ability;
import com.gayasystem.games.dnd.common.characters.scores.AbilityScores;

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
                armorClass
        );
    }
}
