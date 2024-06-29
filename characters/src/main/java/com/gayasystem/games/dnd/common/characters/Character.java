package com.gayasystem.games.dnd.common.characters;

import com.gayasystem.games.dnd.common.characters.alignments.Alignment;
import com.gayasystem.games.dnd.common.characters.equipments.Equipment;
import com.gayasystem.games.dnd.common.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.common.characters.scores.Skills;
import com.gayasystem.games.dnd.lifeform.LifeForm;

import java.util.HashSet;
import java.util.Set;

public class Character extends LifeForm {
    private AbilityScores abilityScores;
    private AbilityScores abilityScoreIncrease;
    private Alignment alignment;
    private Skills skills;
    private int armorClass;
    private Set<Equipment> equipments = new HashSet<>();

    public Character(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, Alignment alignment, Skills skills, int armorClass) {
        this.abilityScores = abilityScores;
        this.abilityScoreIncrease = abilityScoreIncrease;
        this.alignment = alignment;
        this.skills = skills;
        this.armorClass = armorClass;
    }
}
