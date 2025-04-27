package com.gayasystem.games.dnd.lifeforms.sensitive;

import com.gayasystem.games.dnd.common.Thing;
import org.apache.commons.geometry.euclidean.twod.PolarCoordinates;

@Deprecated(forRemoval = true)
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
