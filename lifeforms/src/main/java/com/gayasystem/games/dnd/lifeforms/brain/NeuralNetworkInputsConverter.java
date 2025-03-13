package com.gayasystem.games.dnd.lifeforms.brain;

import com.gayasystem.games.dnd.lifeforms.brain.memories.SpatialEngram;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class NeuralNetworkInputsConverter {
    public static final int ENGRAM = 0;
    public static final int RADIUS = 1;
    public static final int AZIMUTH = 2;

    public double[] create(Collection<SpatialEngram> spatialEngrams) {
        SpatialEngram closest = null;
        for (var spatialEngram : spatialEngrams) {
            if (closest == null)
                closest = spatialEngram;
            if (spatialEngram.origin().getAzimuth() < closest.origin().getAzimuth()) {
                closest = spatialEngram;
            }
        }
        var inputs = new double[]{0, 0, 0};
        if (closest != null) {
            inputs[ENGRAM] = closest.engram().thingClass().hashCode();
            inputs[RADIUS] = closest.origin().getRadius();
            inputs[AZIMUTH] = closest.origin().getAzimuth();
        }
        return inputs;
    }
}
