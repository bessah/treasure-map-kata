package com.bs.treasuremap.game.infrastructure.mappers;

import com.bs.treasuremap.game.models.Game;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class GameMapper {
    private final GameMapMapper gameMapMapper;
    private final MountainMapper mountainMapper;
    private final TreasuresMapper treasuresMapper;

    public GameMapper(GameMapMapper gameMapMapper, MountainMapper mountainMapper, TreasuresMapper treasuresMapper) {
        this.gameMapMapper = gameMapMapper;
        this.mountainMapper = mountainMapper;
        this.treasuresMapper = treasuresMapper;
    }

    public Game toGame(List<String> gameData) {
        Map<String, List<String[]>> dataByType = groupByType(gameData);

        var gameMap = gameMapMapper.toGameMap(dataByType.get("C").get(0));
        var mountains = mountainMapper.toMountainsPositions(dataByType.get("M"));
        var treasures = treasuresMapper.toTreasures(dataByType.get("T"));

        gameMap.putMountains(mountains);
        gameMap.putTreasures(treasures);

        return new Game(
                gameMap,
                null
        );
    }

    private Map<String, List<String[]>> groupByType(List<String> gameData) {
        return gameData.stream()
                .filter(line -> !line.startsWith("#"))
                .map(line -> line.replace(" ", ""))
                .map(line -> line.split("-"))
                .collect(groupingBy(fields -> fields[0]));
    }
}
