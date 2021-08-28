package com.bs.treasuremap.game.models;

import com.bs.treasuremap.adventurer.models.Adventurer;
import com.bs.treasuremap.map.models.GameMap;

import java.util.List;

public class Game {
    private final GameMap map;
    private final List<Adventurer> adventurers;

    public Game(GameMap map, List<Adventurer> adventurers) {
        this.map = map;
        this.adventurers = adventurers;
    }
}
