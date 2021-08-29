package com.bs.treasuremap.game.infrastructure.mappers;

import com.bs.treasuremap.common.models.Position;

import java.util.List;
import java.util.stream.Collectors;

public class MountainMapper {
    public List<Position> toMountainsPositions(List<String[]> mountainsLines) {
        return mountainsLines.stream()
                .map(line -> new Position(Integer.parseInt(line[1]), Integer.parseInt(line[2])))
                .collect(Collectors.toList());
    }

    public List<String> toMountainsLines(List<Position> mountainsPositions) {
        return mountainsPositions.stream()
                .map(pos -> String.format("M - %d - %d", pos.getX(), pos.getY()))
                .collect(Collectors.toList());
    }
}
