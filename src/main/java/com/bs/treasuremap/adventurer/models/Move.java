package com.bs.treasuremap.adventurer.models;

import com.bs.treasuremap.map.models.GameMap;

import java.util.function.BiConsumer;

public enum Move {
    MOVE_FORWARD(Adventurer::moveForward),
    TURN_RIGHT((a, m) -> a.turnRight()),
    TURN_LEFT((a, m) -> a.turnLeft());

    private final BiConsumer<Adventurer, GameMap> action;

    Move(BiConsumer<Adventurer, GameMap> action) {
        this.action = action;
    }

    public void execute(Adventurer adventurer, GameMap gameMap) {
        this.action.accept(adventurer, gameMap);
    }
}
