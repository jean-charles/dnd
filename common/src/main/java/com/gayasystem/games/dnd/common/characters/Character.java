package com.gayasystem.games.dnd.common.characters;

import com.gayasystem.games.dnd.common.characters.alignments.Alignment;
import com.gayasystem.games.dnd.common.characters.equipments.Equipment;
import com.gayasystem.games.dnd.common.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.common.characters.scores.Skills;

import java.util.Set;

public class Character {
    protected AbilityScores abilityScores;
    protected AbilityScores abilityScoreIncrease;
    protected Alignment alignment;
    protected Skills skills;
    protected int armorClass;
    protected Set<Equipment> equipments;
}
