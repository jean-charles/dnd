package com.gayasystem.games.dnd.ecosystem.classes;

import com.gayasystem.games.dnd.gametools.dices.Die1D10;
import com.gayasystem.games.dnd.gametools.dices.Die1D20;
import com.gayasystem.games.dnd.gametools.scores.Ability;
import com.gayasystem.games.dnd.gametools.scores.AbilityScores;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Fighter extends Class {
    public Fighter() {
        super(
                Die1D10.die,
                new AbilityScores(
                        new Ability(Die1D20.die.roll()),
                        new Ability(Die1D20.die.roll()),
                        null,
                        null,
                        null,
                        null
                ),
                new AbilityScores(
                        new Ability(Die1D20.die.roll()),
                        null,
                        new Ability(Die1D20.die.roll()),
                        null,
                        null,
                        null
                ),
                null,
                null);
    }
}
