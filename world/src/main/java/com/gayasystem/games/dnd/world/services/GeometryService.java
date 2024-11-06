package com.gayasystem.games.dnd.world.services;

import org.apache.commons.geometry.spherical.oned.Point1S;
import org.apache.commons.geometry.spherical.oned.Transform1S;
import org.springframework.stereotype.Service;

@Service
public class GeometryService {
    public Point1S rotate(Point1S p, double angle) {
        var t = Transform1S.createRotation(angle);
        var newPoint = t.apply(p);
        var normalizedAzimuth = newPoint.getNormalizedAzimuth();
        return Point1S.of(normalizedAzimuth);
    }
}
