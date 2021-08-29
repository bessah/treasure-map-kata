package com.bs.treasuremap.game.infrastructure.mappers;

import com.bs.treasuremap.common.models.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreasuresMapper {
    public Map<Position, Integer> toTreasures(List<String[]> treasuresLines) {
        return treasuresLines.stream().collect(Collectors.toMap(
                line -> new Position(Integer.parseInt(line[1]), Integer.parseInt(line[2])),
                line -> Integer.parseInt(line[3])
        ));
    }

    public List<String> toTreasuresLines(Map<Position, Integer> treasures) {
        return treasures.entrySet().stream()
                .map(treasure -> String.format("T - %d - %d - %d", treasure.getKey().getX(), treasure.getKey().getY(), treasure.getValue()))
                .collect(Collectors.toList());
    }
}
