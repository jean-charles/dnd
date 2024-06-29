package com.gayasystem.games.dnd.characters;

import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.characters.scores.Skills;
import com.gayasystem.games.dnd.gametools.dices.Dice;

public class Beast extends Character {
    private Dice hitDice;

    public Beast(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, double speed, Alignment alignment, Skills skills, int armorClass, Dice hitDice) {
        super(abilityScores, abilityScoreIncrease, speed, alignment, skills, armorClass);
        this.hitDice = hitDice;
    }
}
