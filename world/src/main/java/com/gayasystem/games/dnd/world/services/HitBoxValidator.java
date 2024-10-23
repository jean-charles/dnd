package com.gayasystem.games.dnd.world.services;

import com.gayasystem.games.dnd.world.InGameObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HitBoxValidator {
    @Autowired
    HitBoxUtils utils;

    public boolean intersection(InGameObject obj1, InGameObject obj2) {
        var rec1 = utils.alignedRectangle(obj1);
        var rec2 = utils.alignedRectangle(obj2);

        return false;
    }
}
