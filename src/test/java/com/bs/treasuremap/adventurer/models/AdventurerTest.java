package com.bs.treasuremap.adventurer.models;

import com.bs.treasuremap.common.models.Orientation;
import com.bs.treasuremap.common.models.Position;
import com.bs.treasuremap.map.models.CellType;
import com.bs.treasuremap.map.models.GameMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdventurerTest {
    @Test
    void orientation_should_be_EAST_when_turnRight_and_previous_orientation_is_NORTH() {
        turnRightTest(Orientation.NORTH, Orientation.EAST);
    }

    @Test
    void orientation_should_be_SOUTH_when_turnRight_and_previous_orientation_is_EAST() {
        turnRightTest(Orientation.EAST, Orientation.SOUTH);
    }

    @Test
    void orientation_should_be_WEST_when_turnRight_and_previous_orientation_is_SOUTH() {
        turnRightTest(Orientation.SOUTH, Orientation.WEST);
    }

    @Test
    void orientation_should_be_NORTH_when_turnRight_and_previous_orientation_is_WEST() {
        turnRightTest(Orientation.WEST, Orientation.NORTH);
    }

    @Test
    void orientation_should_be_WEST_when_turnLeft_and_previous_orientation_is_NORTH() {
        turnLeftTest(Orientation.NORTH, Orientation.WEST);
    }

    @Test
    void orientation_should_be_NORTH_when_turnLeft_and_previous_orientation_is_EAST() {
        turnLeftTest(Orientation.EAST, Orientation.NORTH);
    }

    @Test
    void orientation_should_be_EAST_when_turnLeft_and_previous_orientation_is_SOUTH() {
        turnLeftTest(Orientation.SOUTH, Orientation.EAST);
    }

    @Test
    void orientation_should_be_SOUTH_when_turnLeft_and_previous_orientation_is_WEST() {
        turnLeftTest(Orientation.WEST, Orientation.SOUTH);
    }

    private void turnRightTest(Orientation previousOrientation, Orientation expectedOrientation) {
        // Given
        Adventurer adventurer = adventurer(previousOrientation);
        // When
        adventurer.turnRight();
        // Then
        assertThat(adventurer.getOrientation()).isEqualTo(expectedOrientation);
    }

    @Test
    void adventurer_should_not_move_when_forward_position_is_not_in_the_map() {
        // Given
        Position initialPosition = new Position(1, 0);
        Adventurer adventurer = adventurer("Lara", Orientation.NORTH, initialPosition);
        GameMap gameMap = gameMap(adventurer);
        // When
        adventurer.moveForward(gameMap);
        // Then
        assertThat(adventurer.getPosition()).isEqualTo(initialPosition);
        assertThat(gameMap.getCell(initialPosition).getAdventurer()).isEqualTo(adventurer);
    }


    @Test
    void adventurer_should_not_move_when_forward_cell_is_a_mountain() {
        // Given
        Position initialPosition = new Position(1, 1);
        Adventurer adventurer = adventurer("Lara", Orientation.NORTH, initialPosition);
        GameMap gameMap = gameMap(adventurer);
        // When
        adventurer.moveForward(gameMap);
        // Then
        assertThat(adventurer.getPosition()).isEqualTo(initialPosition);
        assertThat(gameMap.getCell(initialPosition).getAdventurer()).isEqualTo(adventurer);
    }

    @Test
    void adventurer_should_not_move_when_forward_cell_contains_another_adventurer() {
        // Given
        Position initialPosition = new Position(1, 2);
        Adventurer adventurer = adventurer("Lara", Orientation.NORTH, initialPosition);
        Adventurer anotherAdventurer = adventurer("Indiana", Orientation.NORTH, new Position(1, 1));
        GameMap gameMap = gameMap(adventurer, anotherAdventurer);
        // When
        adventurer.moveForward(gameMap);
        // Then
        assertThat(adventurer.getPosition()).isEqualTo(initialPosition);
        assertThat(gameMap.getCell(initialPosition).getAdventurer()).isEqualTo(adventurer);
    }

    @Test
    void adventurer_should_move_when_forward_position_is_free() {
        // Given
        Position initialPosition = new Position(1, 2);
        Adventurer adventurer = adventurer("Lara", Orientation.NORTH, initialPosition);
        GameMap gameMap = gameMap(adventurer);
        // When
        adventurer.moveForward(gameMap);
        // Then
        Position expectedPosition = new Position(1, 1);
        assertThat(adventurer.getPosition()).isEqualTo(expectedPosition);
        assertThat(gameMap.getCell(expectedPosition).getAdventurer()).isEqualTo(adventurer);
    }

    private void turnLeftTest(Orientation previousOrientation, Orientation expectedOrientation) {
        // Given
        Adventurer adventurer = adventurer(previousOrientation);
        // When
        adventurer.turnLeft();
        // Then
        assertThat(adventurer.getOrientation()).isEqualTo(expectedOrientation);
    }

    Adventurer adventurer(Orientation orientation) {
        return adventurer("Lara", orientation, new Position(1, 1));
    }

    Adventurer adventurer(String name, Orientation orientation, Position position) {
        return new Adventurer(name, position, orientation, null);
    }

    GameMap gameMap(Adventurer... adventurer) {
        GameMap map = new GameMap(3, 4);
        map.getCell(0, 1).setType(CellType.MOUNTAIN);
        map.getCell(1, 2).setType(CellType.MOUNTAIN);
        map.getCell(3, 0).setTreasuresNumber(2);
        map.getCell(3, 1).setTreasuresNumber(3);
        map.putAdventurers(List.of(adventurer));
        return map;
    }
}