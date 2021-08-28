package com.bs.treasuremap.map.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameMapTest {
    @Test
    void cells_should_be_initialized_as_plains_when_instantiate_gameMap() {
        // Given
        int width = 3;
        int height = 4;
        // When
        GameMap actual = new GameMap(width, height);
        // Then
        for (var i = 0; i < height; i++) {
            for (var j = 0; j < width; j++) {
                assertThat(actual.getCell(i, j).getType()).isEqualTo(CellType.PLAIN);
            }
        }
    }
}