package com.bs.treasuremap.common.models;

import java.util.Map;

public enum Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

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

    public Orientation rightOrientation() {
        return RIGHT_ORIENTATION.get(this);
    }

    public Orientation leftOrientation() {
        return LEFT_ORIENTATION.get(this);
    }
}
