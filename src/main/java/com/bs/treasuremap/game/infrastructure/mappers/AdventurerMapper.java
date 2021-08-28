package com.bs.treasuremap.game.infrastructure.mappers;

import com.bs.treasuremap.adventurer.models.Adventurer;
import com.bs.treasuremap.adventurer.models.Move;
import com.bs.treasuremap.common.models.Orientation;
import com.bs.treasuremap.common.models.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class AdventurerMapper {
    public List<Adventurer> toAdventurers(List<String[]> adventurersLines) {
        return adventurersLines.stream()
                .map(this::toAdventurer)
                .collect(Collectors.toList());
    }

    private Adventurer toAdventurer(String[] adventurerLine) {
        return new Adventurer(
                adventurerLine[1],
                new Position(Integer.parseInt(adventurerLine[2]), Integer.parseInt(adventurerLine[3])),
                toOrientation(adventurerLine[4]),
                toMoves(adventurerLine[5])
        );
    }

    private Orientation toOrientation(String orientationCode) {
        switch (orientationCode) {
            case "N":
                return Orientation.NORTH;
            case "E":
                return Orientation.EAST;
            case "S":
                return Orientation.SOUTH;
            case "O":
                return Orientation.WEST;
            default:
                throw new IllegalArgumentException(String.format("Provided orientation code '%s' is not recognized", orientationCode));
        }
    }

    private Queue<Move> toMoves(String movesCodes) {
        Queue<Move> moves = new LinkedList<>();
        for (char moveCode : movesCodes.toCharArray()) {
            moves.add(toMove(moveCode));
        }
        return moves;
    }

    private Move toMove(char moveCode) {
        switch (moveCode) {
            case 'A':
                return Move.MOVE_FORWARD;
            case 'D':
                return Move.TURN_RIGHT;
            case 'G':
                return Move.TURN_LEFT;
            default:
                throw new IllegalArgumentException(String.format("Provided move code '%s' is not recognized", moveCode));
        }
    }
}
