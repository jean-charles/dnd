package com.gayasystem.games.dnd.ecosystem;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.hear.SoundSpectrum;
import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.dices.Die;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.gametools.scores.Skills;
import com.gayasystem.games.dnd.lifeforms.Gender;
import com.gayasystem.games.dnd.lifeforms.brain.memories.emotions.Emotion;

import java.util.Map;

public abstract class Beast extends Character {
    private Die hitDie;

    public Beast(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, Alignment alignment, Skills skills, Die hitDie, double width, double depth, double mass, Gender gender, double speed, double sightDistance, double nightSightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Emotion defaultEmotion, Map<Class<? extends Thing>, Emotion> longTermMemories) {
        super(abilityScores, abilityScoreIncrease, alignment, skills, width, depth, mass, gender, speed, sightDistance, nightSightDistance, soundSpectrum, minSoundAmplitude, defaultEmotion, longTermMemories);
        this.hitDie = hitDie;
    }
}
