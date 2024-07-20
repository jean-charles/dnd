package com.gayasystem.games.dnd.characters;

import com.gayasystem.games.dnd.characters.alignments.Alignment;
import com.gayasystem.games.dnd.characters.scores.AbilityScores;
import com.gayasystem.games.dnd.characters.scores.Skills;
import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.gametools.dices.Dice;
import com.gayasystem.games.dnd.lifeforms.brain.sounds.SoundSpectrum;

import java.util.Collection;

public abstract class Beast extends Character {
    private Dice hitDice;

    public Beast(AbilityScores abilityScores, AbilityScores abilityScoreIncrease, Alignment alignment, Skills skills, int armorClass, Dice hitDice, double mass, double speed, double sightDistance, SoundSpectrum soundSpectrum, double minSoundAmplitude, Collection<Class<? extends Thing>> scaredBy, Collection<Class<? extends Thing>> attractedBy) {
        super(abilityScores, abilityScoreIncrease, alignment, skills, armorClass, mass, speed, sightDistance, soundSpectrum, minSoundAmplitude, scaredBy, attractedBy);
        this.hitDice = hitDice;
    }
}
