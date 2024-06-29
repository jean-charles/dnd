package com.gayasystem.games.dnd.ecosystem.characters;

import com.gayasystem.games.dnd.characters.Character;
import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;

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
                30,
                alignment,
                null,
                armorClass
        );
    }
}
