package com.gayasystem.games.dnd.ecosystem.classes;

import com.gayasystem.games.dnd.gametools.dices.Die1D20;
import com.gayasystem.games.dnd.gametools.dices.Die1D8;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Druid extends Class {
    public Druid() {
        super(
                Die1D8.die,
                new AbilityScores(
                        null,
                        null,
                        null,
                        null,
                        new Ability(Die1D20.die.roll()),
                        null
                ),
                new AbilityScores(
                        null,
                        null,
                        null,
                        new Ability(Die1D20.die.roll()),
                        new Ability(Die1D20.die.roll()),
                        null
                ),
                null,
                null);
    }
}
