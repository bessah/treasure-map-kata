package com.bs.treasuremap.map.models;

import com.bs.treasuremap.adventurer.models.Adventurer;
import com.bs.treasuremap.common.models.Orientation;
import com.bs.treasuremap.common.models.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellTest {
    @Test
    void adventurer_should_collect_treasure_when_visit_a_cell_with_treasures() {
        // Given
        Cell cell = cell(3);
        Adventurer adventurer = adventurer();
        // When
        cell.putAdventurer(adventurer);
        // Then
        assertThat(cell.getTreasuresNumber()).isEqualTo(2);
        assertThat(adventurer.getCollectedTreasures()).isEqualTo(1);
    }

    @Test
    void adventurer_should_collect_nothing_when_visit_a_cell_without_treasures() {
        // Given
        Cell cell = cell(0);
        Adventurer adventurer = adventurer();
        // When
        cell.putAdventurer(adventurer);
        // Then
        assertThat(cell.getTreasuresNumber()).isEqualTo(0);
        assertThat(adventurer.getCollectedTreasures()).isEqualTo(0);
    }

    Adventurer adventurer() {
        return new Adventurer("Lara", new Position(1, 1), Orientation.NORTH, null);
    }

    Cell cell(int treasuresNumber) {
        Cell cell = new Cell(CellType.PLAIN);
        cell.setTreasuresNumber(treasuresNumber);
        return cell;
    }
}