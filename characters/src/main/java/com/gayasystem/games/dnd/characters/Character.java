package com.gayasystem.games.dnd.characters;

import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.equipments.Equipment;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.characters.scores.Skills;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.SoundSpectrum;

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

    public Character(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, Alignment alignment, Skills skills, int armorClass, double mass, double speed, double sightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
        super(mass, speed, sightDistance, soundSpectrum, minSoundAmplitude, scaredBy, attractedBy);
        this.abilityScores = abilityScores;
        this.abilityScoreIncrease = abilityScoreIncrease;
        this.alignment = alignment;
        this.skills = skills;
        this.armorClass = armorClass;
    }
}
