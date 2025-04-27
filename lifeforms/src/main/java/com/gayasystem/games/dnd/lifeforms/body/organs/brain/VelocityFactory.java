package com.gayasystem.games.dnd.lifeforms.body.organs.brain;

import com.gayasystem.games.dnd.common.Velocity;
import org.apache.commons.geometry.spherical.oned.Point1S;
import org.springframework.stereotype.Component;

import static java.lang.Math.PI;

@Component
public class VelocityFactory {
    public static final int SPEED = 0;
    //    public static final int ACCELERATION = 1;
    public static final int AZIMUTH = 1;

    public Velocity create(double[] outputs) {
        double speed = outputs[SPEED];
//        double acceleration = outputs[ACCELERATION];
        Point1S azimuth = Point1S.of(outputs[AZIMUTH] * PI);
        return new Velocity(speed, 0.0, azimuth);
    }
}
