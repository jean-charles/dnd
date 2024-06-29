package com.gayasystem.games.dnd.common.characters;

import com.gayasystem.games.dnd.common.characters.alignments.Alignment;
import com.gayasystem.games.dnd.common.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.common.characters.scores.Skills;
import com.gayasystem.games.dnd.common.dices.Dice;

public class Beast extends Character {
    private Dice hitDice;

    public Beast(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, Alignment alignment, Skills skills, int armorClass, Dice hitDice) {
        super(abilityScores, abilityScoreIncrease, alignment, skills, armorClass);
        this.hitDice = hitDice;
    }
}
