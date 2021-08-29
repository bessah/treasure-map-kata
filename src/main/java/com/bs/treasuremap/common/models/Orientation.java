package com.bs.treasuremap.common.models;

import java.util.Map;
import java.util.function.UnaryOperator;

public enum Orientation {
    NORTH(p -> new Position(p.getX(), p.getY() - 1)),
    EAST(p -> new Position(p.getX() + 1, p.getY())),
    SOUTH(p -> new Position(p.getX(), p.getY() + 1)),
    WEST(p -> new Position(p.getX() - 1, p.getY()));

    private static final Map<Orientation, Orientation> RIGHT_ORIENTATION = Map.of(
            NORTH, EAST,
            EAST, SOUTH,
            SOUTH, WEST,
            WEST, NORTH
    );

    private static final Map<Orientation, Orientation> LEFT_ORIENTATION = Map.of(
            NORTH, WEST,
            EAST, NORTH,
            SOUTH, EAST,
            WEST, SOUTH
    );

    private final UnaryOperator<Position> forwardPosition;

    Orientation(UnaryOperator<Position> forwardPosition) {
        this.forwardPosition = forwardPosition;
    }

    public Orientation rightOrientation() {
        return RIGHT_ORIENTATION.get(this);
    }

    public Orientation leftOrientation() {
        return LEFT_ORIENTATION.get(this);
    }

    public Position forwardPosition(Position position) {
        return this.forwardPosition.apply(position);
    }
}
