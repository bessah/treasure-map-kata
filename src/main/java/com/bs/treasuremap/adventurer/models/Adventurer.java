package com.bs.treasuremap.adventurer.models;

import com.bs.treasuremap.common.models.Orientation;
import com.bs.treasuremap.common.models.Position;

import java.util.Queue;

public class Adventurer {
    private String name;
    private Position position;
    private Orientation orientation;
    private Queue<Move> moves;

    public Adventurer(String name, Position position, Orientation orientation, Queue<Move> moves) {
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.moves = moves;
    }

    public void turnRight() {
        this.orientation = this.orientation.rightOrientation();
    }

    public void turnLeft() {
        this.orientation = this.orientation.leftOrientation();
    }

    // getters
    public Orientation getOrientation() {
        return orientation;
    }
}
