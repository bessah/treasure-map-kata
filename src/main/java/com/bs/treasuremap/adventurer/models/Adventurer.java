package com.bs.treasuremap.adventurer.models;

import com.bs.treasuremap.common.models.Orientation;
import com.bs.treasuremap.common.models.Position;
import com.bs.treasuremap.map.models.Cell;

import java.util.Queue;

public class Adventurer {
    private String name;
    private Position position;
    private Orientation orientation;
    private Queue<Move> moves;
    private int collectedTreasures;

    public Adventurer(String name, Position position, Orientation orientation, Queue<Move> moves) {
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.moves = moves;
        this.collectedTreasures = 0;
    }

    public void turnRight() {
        this.orientation = this.orientation.rightOrientation();
    }

    public void turnLeft() {
        this.orientation = this.orientation.leftOrientation();
    }

    public void collectTreasure(Cell cell) {
        cell.withdrawTreasure();
        this.collectedTreasures++;
    }

    // getters
    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getCollectedTreasures() {
        return collectedTreasures;
    }
}
