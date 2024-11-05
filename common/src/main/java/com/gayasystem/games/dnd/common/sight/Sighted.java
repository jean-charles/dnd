package com.gayasystem.games.dnd.common.sight;

import com.gayasystem.games.dnd.common.Thing;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

public interface Sighted {
    /**
     * Called by the world to show other things.
     *
     * @param thing       Seeing thing.
     * @param origin      Relative polar coordinate of the seeing thing.
     * @param orientation Relative orientation of the seeing thing.
     */
    void see(Thing thing, PolarCoordinates origin, double orientation);
}
