package com.bs.treasuremap.game.models;

import com.bs.treasuremap.adventurer.models.Adventurer;
import com.bs.treasuremap.adventurer.models.Move;
import com.bs.treasuremap.common.models.Orientation;
import com.bs.treasuremap.common.models.Position;
import com.bs.treasuremap.map.models.CellType;
import com.bs.treasuremap.map.models.GameMap;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    @Test
    void adventurer_should_be_in_expected_state_at_the_end_of_the_game() {
        // Given
        Adventurer adventurer = adventurer();
        GameMap gameMap = gameMap(adventurer);
        Game game = new Game(gameMap, List.of(adventurer));
        // When
        game.run();
        // Then
        Adventurer expected = expectedAdventurer();
        assertThat(adventurer).usingRecursiveComparison().isEqualTo(expected);
    }

    private GameMap gameMap(Adventurer adventurer) {
        GameMap map = new GameMap(3, 4);
        map.getCell(0, 1).setType(CellType.MOUNTAIN);
        map.getCell(1, 2).setType(CellType.MOUNTAIN);
        map.getCell(3, 0).setTreasuresNumber(2);
        map.getCell(3, 1).setTreasuresNumber(3);
        map.putAdventurers(List.of(adventurer()));
        return map;
    }

    private Adventurer adventurer() {
        Queue<Move> moves = new LinkedList<>();
        moves.add(Move.MOVE_FORWARD);
        moves.add(Move.MOVE_FORWARD);
        moves.add(Move.TURN_RIGHT);
        moves.add(Move.MOVE_FORWARD);
        moves.add(Move.TURN_RIGHT);
        moves.add(Move.MOVE_FORWARD);
        moves.add(Move.TURN_LEFT);
        moves.add(Move.TURN_LEFT);
        moves.add(Move.MOVE_FORWARD);

        return new Adventurer("Lara", new Position(1, 1), Orientation.SOUTH, moves);
    }

    private Adventurer expectedAdventurer() {
        Queue<Move> moves = new LinkedList<>();
        return new Adventurer("Lara", new Position(0, 3), Orientation.SOUTH, moves, 3);
    }
}