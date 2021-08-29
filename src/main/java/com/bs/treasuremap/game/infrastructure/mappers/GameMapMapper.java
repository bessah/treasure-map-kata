package com.bs.treasuremap.game.infrastructure.mappers;

import com.bs.treasuremap.map.models.GameMap;

public class GameMapMapper {
    public GameMap toGameMap(String[] mapLine) {
        return new GameMap(
                Integer.parseInt(mapLine[1]),
                Integer.parseInt(mapLine[2])
        );
    }

    public String toGameMapLine(GameMap gameMap) {
        return String.format("C - %d - %d", gameMap.getWidth(), gameMap.getHeight());
    }
}
