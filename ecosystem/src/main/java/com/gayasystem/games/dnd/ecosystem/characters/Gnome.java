package com.gayasystem.games.dnd.ecosystem.characters;

import com.gayasystem.games.dnd.characters.Character;
import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.Ability;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;

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
