package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.dices.Dice;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.gametools.scores.Skills;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.SoundSpectrum;

import java.util.Collection;

public abstract class Beast extends Character {
    private Dice hitDice;

    public Beast(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, Alignment alignment, Skills skills, int armorClass, Dice hitDice, double mass, Gender gender, double speed, double sightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Collection<Class<? extends Thing>> attractedBy, Collection<Class<? extends Thing>> scaredBy) {
        super(abilityScores, abilityScoreIncrease, alignment, skills, armorClass, mass, gender, speed, sightDistance, soundSpectrum, minSoundAmplitude, attractedBy, scaredBy);
        this.hitDice = hitDice;
    }
}
