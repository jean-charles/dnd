package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.lifeforms.body.organs.muscular.MuscularOrgan;

import java.util.Collection;

public interface Moveable {
    void movement(Collection<MuscularOrgan> organs);
}
