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

    public void run() {
        var end = false;
        while (!end) {
            end = true;

            for (Adventurer adventurer : adventurers) {
                if (adventurer.hasUnExecutedMove()) {
                    end = false;
                    adventurer.executeNextMove(map);
                }
            }
        }
    }

    // getters
    public GameMap getMap() {
        return map;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }
}
