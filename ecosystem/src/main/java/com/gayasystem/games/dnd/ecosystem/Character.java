package com.gayasystem.games.dnd.ecosystem;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.hear.SoundSpectrum;
import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.equipments.Equipment;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.gametools.scores.Skills;
import com.gayasystem.games.dnd.lifeforms.Gender;
import com.gayasystem.games.dnd.lifeforms.LifeForm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Character extends LifeForm {
    private AbilityScores abilityScores;
    private AbilityScores abilityScoreIncrease;
    private Alignment alignment;
    private Skills skills;
    private int armorClass;
    private Set<Equipment> equipments = new HashSet<>();

    public Character(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, Alignment alignment, Skills skills, int armorClass, double mass, Gender gender, double speed, double sightDistance, double nightSightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        super(mass, gender, speed, sightDistance, nightSightDistance, soundSpectrum, minSoundAmplitude, attractedBy, scaredBy);
        this.abilityScores = abilityScores;
        this.abilityScoreIncrease = abilityScoreIncrease;
        this.alignment = alignment;
        this.skills = skills;
        this.armorClass = armorClass;
    }
}
