package com.bs.treasuremap.adventurer.models;

import com.bs.treasuremap.common.models.Orientation;
import com.bs.treasuremap.common.models.Position;
import com.bs.treasuremap.map.models.Cell;
import com.bs.treasuremap.map.models.GameMap;

import java.util.Queue;

public class Adventurer {
    private String name;
    private Position position;
    private Orientation orientation;
    private Queue<Move> moves;
    private int collectedTreasures;

    public Adventurer(String name, Position position, Orientation orientation, Queue<Move> moves) {
        this(name, position, orientation, moves, 0);
    }

    public Adventurer(String name, Position position, Orientation orientation, Queue<Move> moves, int collectedTreasures) {
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.moves = moves;
        this.collectedTreasures = collectedTreasures;
    }

    public boolean hasUnExecutedMove() {
        return !moves.isEmpty();
    }

    public void executeNextMove(GameMap gameMap) {
        var move = moves.poll();
        if (move != null) {
            move.execute(this, gameMap);
        }
    }

    public void moveForward(GameMap gameMap) {
        var toPosition = this.orientation.forwardPosition(this.position);
        if (gameMap.has(toPosition) && gameMap.isFree(toPosition)) {
            gameMap.moveAdventurer(this, this.position, toPosition);
            this.position = toPosition;
        }
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
