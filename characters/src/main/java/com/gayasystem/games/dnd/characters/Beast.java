package com.gayasystem.games.dnd.characters;

import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.characters.scores.Skills;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.gametools.dices.Dice;

import java.util.Collection;

public class Beast extends Character {
    private Dice hitDice;

    public Beast(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, double mass, double speed, Alignment alignment, Skills skills, int armorClass, Dice hitDice, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
        super(abilityScores, abilityScoreIncrease, mass, speed, alignment, skills, armorClass, attractedBy, scaredBy);
        this.hitDice = hitDice;
    }
}
