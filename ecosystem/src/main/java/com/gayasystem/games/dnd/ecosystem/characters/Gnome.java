package com.gayasystem.games.dnd.ecosystem.characters;

import com.gayasystem.games.dnd.common.characters.Character;
import com.gayasystem.games.dnd.common.characters.alignments.Alignment;
import com.gayasystem.games.dnd.common.characters.scores.Ability;
import com.gayasystem.games.dnd.common.characters.scores.AbilityScores;

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
                armorClass
        );
    }
}
