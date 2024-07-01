package com.gayasystem.games.dnd.common;

public record Direction(double degreeX, double degreeY, double degreeZ) {
    public Direction add(Direction origin) {
        double newDegreeX = degreeX + origin.degreeX;
        double newDegreeY = degreeY + origin.degreeY;
        double newDegreeZ = degreeZ + origin.degreeZ;

        return new Direction(newDegreeX, newDegreeY, newDegreeZ);
    }
}
