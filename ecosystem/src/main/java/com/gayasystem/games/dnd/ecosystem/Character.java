package com.gayasystem.games.dnd.ecosystem;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.equipments.Equipment;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.gametools.scores.Skills;
import com.gayasystem.games.dnd.lifeforms.Gender;
import com.gayasystem.games.dnd.lifeforms.LifeForm;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.BrainFactory;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.NeuralNetworkInputsConverter;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.body.organs.muscular.MuscularOrgan;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.SensitiveOrgan;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.stimuli.SoundSpectrum;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Character extends LifeForm {
    private AbilityScores abilityScores;
    private Alignment alignment;
    private Skills skills;
    private int armorClass;
    private Set<Equipment> equipments = new HashSet<>();

    public Character(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, Alignment alignment, Skills skills, double width, double depth, double mass, Gender gender, double speed, double sightDistance, double nightSightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories, final Collection<SensitiveOrgan<?>> sensitiveOrgans, final Collection<MuscularOrgan> muscularOrgans, NeuralNetworkInputsConverter neuralNetworkInputsConverter, BrainFactory brainFactory) {
        super(width, depth, mass, gender, speed, sightDistance, nightSightDistance, soundSpectrum, minSoundAmplitude, defaultEmotion, longTermMemories, sensitiveOrgans, muscularOrgans, neuralNetworkInputsConverter, brainFactory);
        this.abilityScores = calculate(abilityScores, abilityScoreIncrease);
        this.alignment = alignment;
        this.skills = skills;
        this.armorClass = 10 + this.abilityScores.dexterity().modifier();
    }

    private AbilityScores calculate(AbilityScores abilityScores, AbilityScores abilityScoreIncrease) {
        if (abilityScoreIncrease == null)
            return abilityScores;
        return new AbilityScores(
                abilityScores.strength().add(abilityScoreIncrease.strength()),
                abilityScores.dexterity().add(abilityScoreIncrease.dexterity()),
                abilityScores.constitution().add(abilityScoreIncrease.constitution()),
                abilityScores.intelligence().add(abilityScoreIncrease.intelligence()),
                abilityScores.wisdom().add(abilityScoreIncrease.wisdom()),
                abilityScores.charisma().add(abilityScoreIncrease.charisma())
        );
    }
}
