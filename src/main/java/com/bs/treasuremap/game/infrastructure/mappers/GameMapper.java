package com.bs.treasuremap.game.infrastructure.mappers;

import com.bs.treasuremap.game.models.Game;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class GameMapper {
    private final GameMapMapper gameMapMapper;
    private final MountainMapper mountainMapper;
    private final TreasuresMapper treasuresMapper;
    private final AdventurerMapper adventurerMapper;

    public GameMapper(GameMapMapper gameMapMapper, MountainMapper mountainMapper, TreasuresMapper treasuresMapper, AdventurerMapper adventurerMapper) {
        this.gameMapMapper = gameMapMapper;
        this.mountainMapper = mountainMapper;
        this.treasuresMapper = treasuresMapper;
        this.adventurerMapper = adventurerMapper;
    }

    public Game toGame(List<String> gameData) {
        Map<String, List<String[]>> dataByType = groupByType(gameData);

        var gameMap = gameMapMapper.toGameMap(dataByType.get("C").get(0));
        var mountains = mountainMapper.toMountainsPositions(dataByType.get("M"));
        var treasures = treasuresMapper.toTreasures(dataByType.get("T"));
        var adventurers = adventurerMapper.toAdventurers(dataByType.get("A"));

        gameMap.putMountains(mountains);
        gameMap.putTreasures(treasures);
        gameMap.putAdventurers(adventurers);

        return new Game(
                gameMap,
                adventurers
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
