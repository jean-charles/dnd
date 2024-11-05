package com.gayasystem.games.dnd.world.services.domains;

import org.apache.commons.geometry.euclidean.twod.Vector2D;

public record HitBox(Vector2D center, Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4) {
    public HitBox(Vector2D center, double width, double depth) {
        this(center, center.getX(), center.getY(), width / 2, depth / 2);
    }

    private HitBox(Vector2D center, double x, double y, double halfWidth, double halfDepth) {
        this(center,
                Vector2D.of(x + halfDepth, y + halfWidth),
                Vector2D.of(x + halfDepth, y - halfWidth),
                Vector2D.of(x - halfDepth, y - halfWidth),
                Vector2D.of(x - halfDepth, y + halfWidth));
    }
}