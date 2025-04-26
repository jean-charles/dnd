package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.sensitive.Pain;
import com.gayasystem.games.dnd.lifeforms.sensitive.Sighted;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

public class Eye implements Organ, Sighted, Pain {
    @Override
    public void see(final Thing thing, final PolarCoordinates origin, final double orientation) {
    }
}