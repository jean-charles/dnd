package com.gayasystem.games.dnd.ecosystem.races;

import com.gayasystem.games.dnd.ecosystem.Character;
import com.gayasystem.games.dnd.gametools.alignments.Alignment;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import com.gayasystem.games.dnd.lifeforms.Gender;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class HalfOrc extends Character {
    public HalfOrc(AbilityScores abilityScores, Alignment alignment, int armorClass, Gender gender) {
        super(
                abilityScores,
                new AbilityScores(
                        new Ability(2),
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                alignment,
                null,
                armorClass,
                155, // 155 to 225 lb
                gender,
                30,
                3.0,
                0.0,
                null,
                0.0,
                null,
                null
        );
    }
}
