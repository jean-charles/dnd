package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HitBoxValidator {
    @Autowired
    private HitBoxUtils utils;

    public double rotation(InGameObject obj, InGameObject other, double phi) {
        var objHb = utils.hitBox(obj);
        var otherHb = utils.hitBox(other);

        return phi;
    }

    public double translate(InGameObject obj, InGameObject other, double rho) {
        return rho;
    }
}
