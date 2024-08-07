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
public class HalfElf extends Character {
    public HalfElf(AbilityScores abilityScores, Alignment alignment, int armorClass, Gender gender) {
        super(
                abilityScores,
                new AbilityScores(
                        null,
                        null,
                        null,
                        null,
                        null,
                        new Ability(2)
                ),
                alignment,
                null,
                armorClass,
                100, // 100 to 180 lb
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
