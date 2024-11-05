package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.world.InGameObject;
import org.apache.commons.numbers.core.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HitBoxValidator {
    private final Precision.DoubleEquivalence p;

    @Autowired
    private HitBoxUtils utils;

    public HitBoxValidator() {
        this.p = Precision.doubleEquivalenceOfEpsilon(1e-6);
    }

    /**
     * Find the angle that the object can rotate until reach the other object or the desired angle.
     *
     * @param obj   Object to rotate.
     * @param other Object tha can block the object to rotate.
     * @param phi   Desired angle of rotation (radian).
     * @return final angle of the rotation.
     */
    public double rotation(InGameObject obj, InGameObject other, double phi) {
        var objHb = utils.hitBox(obj);
        var otherHb = utils.hitBox(other);

        if (!utils.couldItCrossIt(objHb, otherHb)) {
            return phi;
        }
        var points = utils.intersections(objHb, otherHb);

        return phi;
    }

    public double translate(InGameObject obj, InGameObject other, double rho) {
        return rho;
    }
}
