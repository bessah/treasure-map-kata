package com.bs.treasuremap.game.infrastructure.mappers;

import com.bs.treasuremap.adventurer.models.Adventurer;
import com.bs.treasuremap.game.models.Game;
import com.bs.treasuremap.map.models.CellType;
import com.bs.treasuremap.map.models.GameMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameMapperTest {
    GameMapper gameMapper = new GameMapper(
            new GameMapMapper(),
            new MountainMapper(),
            new TreasuresMapper()
    );

    @Test
    void toGame_should_return_expected_object() {
        // Given
        List<String> gameData = List.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 0 - 3 - 2",
                "T - 1 - 3 - 3"
        );
        // When
        Game actual = gameMapper.toGame(gameData);
        // Then
        Game expected = new Game(gameMap(), adventurers());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private GameMap gameMap() {
        GameMap map = new GameMap(3, 4);
        map.getCell(0, 1).setType(CellType.MOUNTAIN);
        map.getCell(1, 2).setType(CellType.MOUNTAIN);
        map.getCell(3, 0).setTreasuresNumber(2);
        map.getCell(3, 1).setTreasuresNumber(3);
        return map;
    }

    private List<Adventurer> adventurers() {
        return null;
    }
}