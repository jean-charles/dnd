package com.gayasystem.games.dnd.ecosystem.races;

import com.gayasystem.games.dnd.ecosystem.Character;
import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Gender;
import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Halfling extends Character {
    public Halfling(AbilityScores abilityScores, Alignment alignment, Gender gender) {
        super(
                abilityScores,
                new AbilityScores(
                        null,
                        new Ability(2),
                        null,
                        null,
                        null,
                        null
                ),
                alignment,
                null,
                0,
                0,
                40, //40 to 45 lb
                gender,
                25,
                3.0,
                0.0,
                null,
                0.0,
                Emotion.neutral,
                null,
                null
        );
    }
}
