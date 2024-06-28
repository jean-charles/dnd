package com.gayasystem.games.dnd.ecosystem.beasts;

import com.gayasystem.games.dnd.common.characters.Beast;
import com.gayasystem.games.dnd.common.characters.scores.Ability;
import com.gayasystem.games.dnd.common.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.common.characters.scores.Skills;
import com.gayasystem.games.dnd.common.dices.Dice1d6;

public class Almiraj extends Beast {
    public Almiraj() {
        abilityScores = new AbilityScores(
                new Ability(2),
                new Ability(16),
                new Ability(10),
                new Ability(2),
                new Ability(14),
                new Ability(10)
        );
        abilityScoreIncrease = null;
        alignment = null;
        skills = new Skills(0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0);
        armorClass = 13;
        equipments = null;

        hitDice = Dice1d6.dice;
    }
}
