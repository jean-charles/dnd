package com.gayasystem.games.dnd.lifeforms;

import org.springframework.stereotype.Component;

@Component
public class LifeFormA extends LifeForm {
    public LifeFormA() {
        super(LifeFormTest.MASS, LifeFormTest.SPEED, LifeFormTest.SCARED_BY, LifeFormTest.ATTRACTED_BY);
    }
}
