package com.gayasystem.games.dnd.common;

public record SpericalCoordinate(double distance, double theta, double phi) {
    public SpericalCoordinate add(SpericalCoordinate origin) {
        double distance = this.distance + origin.distance;
        double theta = this.theta + origin.theta;
        double phi = this.phi + origin.phi;

        return new SpericalCoordinate(distance, theta, phi);
    }
}
