package com.gayasystem.games.dnd.lifeforms;

import org.springframework.stereotype.Component;

import static com.gayasystem.games.dnd.lifeforms.LifeFormTest.*;

@Component
public class LifeFormA extends LifeForm {
    public LifeFormA() {
        super(MASS, SPEED, SIGHT_DISTANCE, SOUND_SPECTRUM, MIN_SOUND_AMPLITUDE, LifeFormTest.SCARED_BY, LifeFormTest.ATTRACTED_BY);
    }
}
