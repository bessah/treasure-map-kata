package com.bs.treasuremap.common.models;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
