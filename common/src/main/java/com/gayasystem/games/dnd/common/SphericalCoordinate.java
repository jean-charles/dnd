package com.gayasystem.games.dnd.common;

public record SphericalCoordinate(double distance, double theta, double phi) {
    public SphericalCoordinate add(SphericalCoordinate origin) {
        double distance = this.distance + origin.distance;
        double theta = this.theta + origin.theta;
        double phi = this.phi + origin.phi;

        return new SphericalCoordinate(distance, theta, phi);
    }

    public SphericalCoordinate opposite() {
        return add(new SphericalCoordinate(0, 180, 180));
    }
}
